package com.code93.e_movie.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.code93.e_movie.R
import com.code93.e_movie.databinding.FragmentDetailBinding
import com.code93.e_movie.databinding.FragmentHomeBinding
import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.ui.home.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val detailViewModel by viewModels<DetailViewModel>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getDetail(args.idMovie)
        initUi()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect {
                    when (it) {
                        is DetailState.Error -> showToast("Loading")
                        DetailState.Loading -> showToast("Loading")
                        is DetailState.Success -> loadMovie(it.detailsModel)
                    }
                }
            }
        }
    }

    private fun loadMovie(detailsModel: DetailsModel) {
        binding.tvTitleMovie.text = detailsModel.title
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    private fun initUi() {
        binding.ivPoster.load(args.posterPath)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

}