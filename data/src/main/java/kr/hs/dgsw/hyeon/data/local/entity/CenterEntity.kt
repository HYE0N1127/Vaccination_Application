package kr.hs.dgsw.hyeon.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "center_table")
data class CenterEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "center_name") val centerName: String,
    @ColumnInfo(name = "facility_name") val facilityName: String,
    @ColumnInfo(name = "phone_number") val phone_number: String,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "lat") val lat: String,
    @ColumnInfo(name = "lng") val lng: String
)
