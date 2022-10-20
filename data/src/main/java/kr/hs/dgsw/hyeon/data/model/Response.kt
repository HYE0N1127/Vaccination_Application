package kr.hs.dgsw.hyeon.data.model

data class Response<T>(
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
    val currentCount: Int,
    val matchCount: Int,
    val data: T
)
