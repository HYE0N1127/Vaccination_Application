package kr.hs.dgsw.hyeon.data.service

import kr.hs.dgsw.hyeon.data.model.Response
import kr.hs.dgsw.hyeon.data.model.CenterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CenterService {
    @GET("15077586/v1/centers")
    suspend fun getCenterDataByRemote(
        @Query("page") page: Int
    ): Response<List<CenterResponse>>
}