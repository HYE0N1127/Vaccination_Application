package kr.hs.dgsw.hyeon.vaccination_application.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.UiThread
import androidx.core.app.ActivityCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.vaccination_application.R
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseActivity
import kr.hs.dgsw.hyeon.vaccination_application.databinding.ActivityMainBinding
import kr.hs.dgsw.hyeon.vaccination_application.viewmodel.MainViewModel
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnMapReadyCallback {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var map: NaverMap

    private val markers = mutableListOf<Pair<Marker, Center>>()

    var locationManager: LocationManager? = null
    private val REQUEST_CODE_LOCATION: Int = 2
    var currentLocation: String = ""
    var latitude: Double? = null
    var longitude: Double? = null

    override fun createBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_layout) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_layout, it).commit()
            }
        mapFragment.getMapAsync(this)

        getCurrentLoc()
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        val uiSettings = naverMap.uiSettings
        val overlay = naverMap.locationOverlay

        naverMap.let {
            map = it
        }

        naverMap.mapType = NaverMap.MapType.Navi
        uiSettings.isZoomControlEnabled = false
        uiSettings.isScaleBarEnabled = false

        naverMap.minZoom = 6.5
        overlay.isMinZoomInclusive = true
        naverMap.maxZoom = 14.5
        overlay.isMaxZoomInclusive = true

        viewModel.getUserData()
    }

    override fun initObserver() {
        with(mViewModel) {
            localCenterList.observe(this@MainActivity) { itemList ->

                markers.forEach { marker ->
                    marker.first.map = null
                }
                markers.clear()

                itemList.forEach {
                    markers += Marker().apply {
                        position = LatLng(it.lat.toDouble(), it.lng.toDouble())
                        height = 50
                        width = 50
                    } to it

                }

                markers.forEach { (markerItem, center) ->
                    markerItem.map = map

                    markerItem.setOnClickListener {
                        centerInformationDialog(center)
                        val cameraUpdate = CameraUpdate.scrollTo(
                            LatLng(
                                center.lat.toDouble(),
                                center.lng.toDouble()
                            )
                        )
                        map.moveCamera(cameraUpdate)
                        true
                    }
                }
            }
            binding.btnLocation.setOnClickListener {
                getCurrentLoc()
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude!!, longitude!!))
                map.moveCamera(cameraUpdate)
            }
        }
    }

    private fun centerInformationDialog(center: Center) {
        val dialog = AlertDialog.Builder(this).apply {
            setTitle("접종 센터 정보")
            setMessage(
                "주소 : ${center.address}" + "\n" +
                        "센터명 : ${center.centerName}" + "\n" +
                        "시설명 : ${center.facilityName}" + "\n" +
                        "전화번호 : ${center.phoneNumber}" + "\n" +
                        "업데이트 날짜 : ${center.updateAt}"
            )
            setNegativeButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    private fun getCurrentLoc() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        val userLocation: Location = getLatLng()
        latitude = userLocation.latitude
        longitude = userLocation.longitude

        var mGeocoder = Geocoder(applicationContext, Locale.KOREAN)
        var mResultList: List<Address>? = null
        try {
            mResultList = mGeocoder.getFromLocation(
                latitude!!, longitude!!, 1
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (mResultList != null) {
            Log.d("CheckCurrentLocation", mResultList[0].getAddressLine(0))
            currentLocation = mResultList[0].getAddressLine(0)
            currentLocation = currentLocation.substring(11)
        }
    }

    private fun getLatLng(): Location {
        var bestLocation: Location? = null
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                this.REQUEST_CODE_LOCATION
            )
            getLatLng()
        } else {
            val providers = locationManager!!.getProviders(true)
            for (provider in providers) {
                val l = locationManager!!.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy)
                    bestLocation = l

            }
        }
        return bestLocation!!
    }
}
