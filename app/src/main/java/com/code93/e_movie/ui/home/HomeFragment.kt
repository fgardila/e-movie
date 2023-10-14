package com.code93.e_movie.ui.home

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.code93.e_movie.R
import com.code93.e_movie.databinding.FragmentHomeBinding
import com.code93.e_movie.domain.model.ISOLanguageCode
import com.code93.e_movie.domain.model.ISOLanguageCode.Companion.fromCode
import com.code93.e_movie.domain.model.ISOLanguageCode.Companion.getCodes
import com.code93.e_movie.domain.model.ISOLanguageCode.Companion.getNames
import com.code93.e_movie.domain.model.ResultModel
import com.code93.e_movie.domain.model.TopRatedModel
import com.code93.e_movie.domain.model.UpcomingModel
import com.code93.e_movie.ui.home.adapters.MovieAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter
    private lateinit var forYouAdapter: MovieAdapter

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
                homeViewModel.stateTopRated.collect {
                    when (it) {
                        is HomeTopRateState.Error -> showToast(it.error)
                        HomeTopRateState.Loading -> showToast("Loading")
                        is HomeTopRateState.SuccessTopRated -> loadTopRated(it.topRatedModel)
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.stateUpcoming.collect {
                    when (it) {
                        is HomeUpcomingState.Error -> showToast(it.error)
                        HomeUpcomingState.Loading -> showToast("Loading")
                        is HomeUpcomingState.SuccessUpcoming -> loadUpcoming(it.upcomingModel)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.listForYou.collect {
                    if (it.isNotEmpty()) {
                        loadForYou(it)
                    } else {
                        loadForYou(emptyList())
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.listLanguage.collect {
                    loadLanguage(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.listReleaseYear.collect {
                    loadReleaseYearChip(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.languageFilter.collect {
                    if (it != null) {
                        binding.buttonLanguage.text = requireContext().getText(it.resource)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.yearFilter.collect {
                    if (it.isNotEmpty()) {
                        binding.buttonYear.text = it
                    }
                }
            }
        }

    }

    private fun loadLanguage(language: List<ISOLanguageCode>) {

        val listCodes = getCodes(language)
        val listNames = getNames(language, requireContext())
        binding.buttonLanguage.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Seleccione un idioma")
                .setPositiveButton("Limpiar filtro") { dialog, which ->

                }
                .setItems(listNames.toTypedArray()) { dialog, which ->
                    binding.buttonYear.setText(R.string.release_year)
                    homeViewModel.setLanguageFilter(fromCode(listCodes[which])!!)
                }
                .show()
        }
    }

    private fun loadReleaseYearChip(listYears: List<String>) {
        binding.buttonYear.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Seleccione un año")
                .setPositiveButton("Limpiar filtro") { dialog, which ->

                }
                .setItems(listYears.toTypedArray()) { dialog, which ->
                    binding.buttonLanguage.setText(R.string.language)
                    binding.buttonYear.text = listYears[which]
                    homeViewModel.setYearFilter(listYears[which])
                }
                .show()
        }
    }

    private fun loadForYou(resultModels: List<ResultModel>) {
        forYouAdapter.setListResultModel(resultModels)
    }

    private fun loadTopRated(topRatedModel: TopRatedModel) {
        topRatedAdapter.setListResultModel(topRatedModel.resultModels)
    }

    private fun loadUpcoming(upcomingModel: UpcomingModel) {
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

        forYouAdapter = MovieAdapter(emptyList()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.id, it.posterPath)
            )
        }
        binding.rvForYou.adapter = forYouAdapter


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
}