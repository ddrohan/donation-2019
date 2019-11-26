package ie.wit.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import ie.wit.R
import ie.wit.main.DonationApp

class FavouritesFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var app: DonationApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DonationApp
        getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.favourites_title)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        Log.v("Donation", "onMapReady LAT: ${app.currentLocation.latitude} LNG: ${app.currentLocation.longitude}")

        val pos = LatLng(app.currentLocation.latitude,app.currentLocation.longitude)
        mMap.addMarker(MarkerOptions().position(pos).title("Marker at Current Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14f))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavouritesFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}