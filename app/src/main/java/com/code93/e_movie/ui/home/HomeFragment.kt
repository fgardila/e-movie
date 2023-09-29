package com.code93.e_movie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.code93.e_movie.databinding.FragmentHomeBinding
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel
import com.code93.e_movie.ui.home.adapters.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.state.collect {
                    when (it) {
                        is HomeState.Error -> showToast("Loading")
                        HomeState.Loading -> showToast("Loading")
                        is HomeState.Success -> loadAdapters(it.topRatedModel, it.upcomingModel)
                    }
                }
            }
        }
    }

    private fun loadAdapters(topRatedModel: TopRatedModel, upcomingModel: UpcomingModel) {
        topRatedAdapter.setListResultModel(topRatedModel.resultModels)
        upcomingAdapter.setListResultModel(upcomingModel.resultModels)
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    private fun initUi() {
        topRatedAdapter = MovieAdapter(emptyList()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id, it.posterPath)
            )
        }
        upcomingAdapter = MovieAdapter(emptyList()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id, it.posterPath)
            )
        }
        binding.rvTopRated.adapter = topRatedAdapter
        binding.rvTopRated.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvUpcoming.adapter = upcomingAdapter
        binding.rvUpcoming.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}