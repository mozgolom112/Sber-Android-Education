package com.mozgolom112.fundamentalsandroid.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.domain.Movie
import com.mozgolom112.fundamentalsandroid.support.KEY_EXTRA_MOVIE
import com.mozgolom112.fundamentalsandroid.support.URL_TO_TRAILER
import com.mozgolom112.fundamentalsandroid.viewmodels.MovieDetailsViewModel
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.MovieDetailsViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie_details.*

private const val PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val PERMISSIONS_REQUEST_CODE = 1

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModelFactory: MovieDetailsViewModelFactory by lazy {
        val movie = arguments?.get(KEY_EXTRA_MOVIE) as Movie
        MovieDetailsViewModelFactory(movie, requireContext())
    }

    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment =
                MovieDetailsFragment()
            addArguments(
                movie,
                fragment
            )
            return fragment
        }

        private fun addArguments(movie: Movie, fragment: MovieDetailsFragment) {
            val args: Bundle = Bundle()
            args.putParcelable(KEY_EXTRA_MOVIE, movie)
            fragment.arguments = args
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setObservers()
        setOnClickListeners()
        setContent()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the contacts-related task you need to do.
                Log.d("DetailsFragment", "onRequestPermissionsResult # Permission granted")
                startDownloadService()
            } else {
                // permission denied, boo! Disable the functionality that depends on this permission.
                Log.d("DetailsFragment", "onRequestPermissionsResult # Permission denied")
            }
        }
    }

    private fun setObservers() {
        viewModel.apply {
            isTrailerFound.observe(viewLifecycleOwner, Observer {isTrailerFound ->
                if (isTrailerFound){
                    //TODO("Add loader")
                    btnvWatchTrailer.visibility = View.VISIBLE
                    btnvWatchTrailer.isClickable = true
                } else {
                    btnvWatchTrailer.visibility = View.INVISIBLE
                    btnvWatchTrailer.isClickable = false
                }
            })
        }
    }

    private fun setOnClickListeners() {
        btnvWatchTrailer.setOnClickListener {
            val trailerURL = viewModel.getTrailerUrl()
            if (trailerURL != null)
                openUrl(trailerURL)
            else {
                //TODO("Hide button")
                Toast.makeText(requireContext(), "Sorry. No trailer here", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        fabDownloadPoster.setOnClickListener {
            onFabClick()
        }
    }

    private fun openUrl(videoUrl: String) {
        Log.i("Youtube intent", "${videoUrl}")
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
        startActivity(intent)
    }

    private fun onFabClick() {
        //ask permission if it has't granted yet
        if (isPermissionGranted) {
            startDownloadService()
        } else {
            requestPermission()
        }

        viewModel.onFabClick()
    }

    private fun startDownloadService() {
        Log.d("DetailsFragment", "startDownloadService")
    }

    private val isPermissionGranted: Boolean
        get() = ContextCompat.checkSelfPermission(requireContext(), PERMISSION) ==
                PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), PERMISSION)) {
            // Show an explanation to the user.
            // After the user sees the explanation, try again to request the permission.
            showExplainingRationaleDialog()
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(PERMISSION),
                PERMISSIONS_REQUEST_CODE
            )
            // PERMISSIONS_REQUEST_CODE is an app-defined int constant.
            // The callback method gets the result of the request.
        }
    }

    private fun showExplainingRationaleDialog() {
        AlertDialog.Builder(requireActivity())
            .setTitle("Write file permission")
            .setMessage("We need this permission for write down file into FS of your phone")
            .setPositiveButton("Allow") { _, _ ->
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(PERMISSION),
                    PERMISSIONS_REQUEST_CODE
                )
            }
            .create()
            .show()
    }

    private fun setContent() {

        val movie = viewModel.movie
        movie?.apply {
            txtvFilmTitle.text = title
            txtvOverviewText.text = overview
            txtvReleaseDate.text = release_date

            //TODO("Refactor code below")
            imgvBackGround.load(backdrop_path) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
            imgvSmallImage.load(poster_path) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }
    }


}