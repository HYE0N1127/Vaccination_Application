package kr.hs.dgsw.hyeon.domain.model

data class Center(
    val id: Int,
    val centerName: String,
    val facilityName: String,
    val address: String,
    val updateAt: String,
    val phoneNumber: String
    val lat: String,
    val lng: String
)
