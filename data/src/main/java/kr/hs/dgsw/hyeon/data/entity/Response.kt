package kr.hs.dgsw.hyeon.data.entity

import kr.hs.dgsw.hyeon.domain.model.Center

data class Response<T>(
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
    val currentCount: Int,
    val matchCount: Int,
    val data: T
)
