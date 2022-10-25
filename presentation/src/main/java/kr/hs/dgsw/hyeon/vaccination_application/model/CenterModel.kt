package kr.hs.dgsw.hyeon.vaccination_application.model

sealed class CenterModel {
    data class Marker(val marker: Marker?): CenterModel()
    data class Center(val address: String, val centerName: String, val facilityName: String, val phoneNumber: String, val updateAt: String): CenterModel()
}