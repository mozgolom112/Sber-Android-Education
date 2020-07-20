package com.example.android.gdgfinder.search

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.showOnlyWhenEmpty
import com.google.android.gms.location.*
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_gdg_list.*
import retrofit2.HttpException

private const val LOCATION_PERMISSION_REQUEST = 1

private const val LOCATION_PERMISSION = "android.permission.ACCESS_FINE_LOCATION"

class GdgListFragment : Fragment() {

    private val viewModel: GdgListViewModel by viewModels()

    private val viewModelAdapter: GdgListAdapter by lazy { GdgListAdapter { chapter ->
        val destination = Uri.parse(chapter.website)
        startActivity(Intent(Intent.ACTION_VIEW, destination))}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_gdg_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclevGdgList.adapter = viewModelAdapter
        setObserver(view)
        setHasOptionsMenu(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestLastLocationOrStartLocationUpdates()
    }

    private fun setObserver(view: View) {
        viewModel.apply {
            showNeedLocation.observe(viewLifecycleOwner, Observer { show ->
                    if (show == true) {
                        showSnackbar(view)
                    }
                })
            gdgList.observe(viewLifecycleOwner, Observer {
                txtvWarningText.showOnlyWhenEmpty(it)
                viewModelAdapter?.submitList(it)
            })
            errorHttpException.observe(viewLifecycleOwner, Observer {
                showHttpExp(it as HttpException)
            })
            regionList.observe(viewLifecycleOwner, Observer { data ->
                val children = setRegionList(data)
                chipgrRegionsList.removeAllViews()
                children.forEach() { chipgrRegionsList.addView(it) }
            })
        }
    }

    private fun setRegionList(data: List<String>): List<Chip> {
        val inflater = LayoutInflater.from(chipgrRegionsList.context)
        return data.map { regionName ->
            val chip = inflater.inflate(R.layout.region, chipgrRegionsList, false) as Chip
            chip.apply {
                text = regionName
                tag = regionName
                setOnCheckedChangeListener { button, isChecked ->
                    viewModel.onFilterChanged(button.tag as String, isChecked)
                }

            }
            chip
        }
    }

    private fun showSnackbar(view: View) {
        Snackbar.make(view, getString(R.string.noLocationError), Snackbar.LENGTH_LONG).show()
    }

    private fun showHttpExp(exp: HttpException){
        Toast.makeText(this.context, "Link doesn't work ${exp.message()}", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun requestLastLocationOrStartLocationUpdates() {
        if (requestLocationPermission()) return

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) startLocationUpdates(fusedLocationClient)
            else viewModel.onLocationUpdated(location)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates(fusedLocationClient: FusedLocationProviderClient) {
        // if we don't have permission ask for it and wait until the user grants it
        if (requestLocationPermission()) return


        val request = LocationRequest().setPriority(LocationRequest.PRIORITY_LOW_POWER)
        val callback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                val location = locationResult?.lastLocation ?: return
                viewModel.onLocationUpdated(location)
            }
        }
        fusedLocationClient.requestLocationUpdates(request, callback, null)
    }

    private fun requestLocationPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                LOCATION_PERMISSION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(LOCATION_PERMISSION), LOCATION_PERMISSION_REQUEST)
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLastLocationOrStartLocationUpdates()
                }
            }
        }
    }
}


