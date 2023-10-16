package com.code93.e_movie.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.code93.e_movie.databinding.FragmentDetailBinding
import com.code93.e_movie.domain.model.CastModel
import com.code93.e_movie.domain.model.DetailsModel
import com.code93.e_movie.domain.model.ResultVideoModel
import com.code93.e_movie.ui.detail.adapters.CastAdapter
import com.code93.e_movie.ui.detail.adapters.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var genreAdapter: GenreAdapter
    private lateinit var castAdapter: CastAdapter

    private val detailViewModel by viewModels<DetailViewModel>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.getMovie(args.idMovie)
        initUi()
        initObservers()
    }

    private fun loadVideo(results: List<ResultVideoModel>) {
        val trailer = results.find { it.type == "Trailer" }
        if (trailer != null && trailer.site == "YouTube") {
            binding.btnTrailer.visibility = View.VISIBLE
            binding.btnTrailer.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=${trailer.key}.")
                    )
                )
            }
        }

    }

    private fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect {
                    when (it) {
                        is DetailState.Error -> showToast("Error")
                        DetailState.LoadingDetail -> showToast("Loading")
                        DetailState.LoadingCast -> TODO()
                        DetailState.LoadingVideo -> disableBottomTrailer()
                        is DetailState.SuccessDetail -> loadDetail(it.detailsModel)
                        is DetailState.SuccessVideo -> loadVideo(it.videoModel.results)
                        is DetailState.SuccessCast -> loadCast(it.listCast)
                    }
                }
            }
        }
    }

    private fun loadCast(listCast: List<CastModel>) {
        castAdapter.setListCast(listCast)
    }

    private fun disableBottomTrailer() {
        binding.btnTrailer.visibility = View.GONE
    }

    private fun loadDetail(detailsModel: DetailsModel) {
        genreAdapter.setListGenre(detailsModel.genres)
        binding.tvTitle.text = detailsModel.title
        binding.tvOverview.text = detailsModel.overview
        binding.tvAge.text = detailsModel.releaseDate.substring(0, 4)
        binding.tvLanguage.text = detailsModel.originalLanguage
        binding.tvRate.text = detailsModel.voteAverage.toString().substring(0, 3)
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    private fun initUi() {

        binding.ivPoster.load(args.posterPath)

        genreAdapter = GenreAdapter(emptyList())
        binding.rvGenre.adapter = genreAdapter
        binding.rvGenre.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        castAdapter = CastAdapter(emptyList())
        binding.rvCast.adapter = castAdapter
        binding.rvCast.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}