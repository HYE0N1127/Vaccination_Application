package kr.hs.dgsw.hyeon.vaccination_application.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.UiThread
import com.naver.maps.geometry.LatLng
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

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnMapReadyCallback {
    override val viewModel: MainViewModel by viewModels()
    private lateinit var map: NaverMap

    private val markers = mutableListOf<Pair<Marker, Center>>()

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
                    Log.d("Test5", "$center")

                    markerItem.setOnClickListener {
                        centerInformationDialog(center)
                        true
                    }
                }
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

}
