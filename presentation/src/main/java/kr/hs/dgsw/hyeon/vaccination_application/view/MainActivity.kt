package kr.hs.dgsw.hyeon.vaccination_application.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.UiThread
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.hyeon.vaccination_application.R
import kr.hs.dgsw.hyeon.vaccination_application.base.BaseMapActivity
import kr.hs.dgsw.hyeon.vaccination_application.databinding.ActivityMainBinding
import kr.hs.dgsw.hyeon.vaccination_application.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseMapActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var map: NaverMap

    private val markers = mutableListOf<Marker>()

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

        naverMap.minZoom = 11.0
        overlay.isMinZoomInclusive = true
        naverMap.maxZoom = 14.5
        overlay.isMaxZoomInclusive = true

        viewModel.getUserData()
    }

    override fun initObserver() {
        with(mViewModel) {
            localCenterList.observe(this@MainActivity) { itemList ->

                markers.forEach { marker ->
                    marker.map = null
                }
                markers.clear()

                itemList.forEach {
                    markers += Marker().apply {
                        position = LatLng(it.lat.toDouble(), it.lng.toDouble())
                        width = 80
                        height = 80
                    }
                }

                markers.forEach { markerItem ->
                    markerItem.map = map
                }
            }
        }
    }
}