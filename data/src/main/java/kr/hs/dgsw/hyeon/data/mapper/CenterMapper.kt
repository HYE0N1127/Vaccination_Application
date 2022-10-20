package kr.hs.dgsw.hyeon.data.mapper

import kr.hs.dgsw.hyeon.data.local.entity.CenterEntity
import kr.hs.dgsw.hyeon.data.model.CenterResponse
import kr.hs.dgsw.hyeon.domain.model.Center

fun CenterResponse.toModel() = Center(
    this.id,
    this.centerName,
    this.facilityName,
    this.address,
    this.updatedAt,
    this.phoneNumber,
    this.lat,
    this.lng
)

fun CenterEntity.toModel() = Center(
    this.id,
    this.centerName,
    this.facilityName,
    this.address,
    this.updatedAt,
    this.phone_number,
    this.lat,
    this.lng
)

fun Center.toEntity() = CenterEntity(
    this.id,
    this.address,
    this.centerName,
    this.facilityName,
    this.phoneNumber,
    this.updateAt,
    this.lat,
    this.lng
)