package kr.hs.dgsw.hyeon.data.service

import kr.hs.dgsw.hyeon.data.BuildConfig
import kr.hs.dgsw.hyeon.data.model.CenterResponse
import kr.hs.dgsw.hyeon.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CenterService {
    @GET("15077586/v1/centers")
    suspend fun getCenterDataByRemote(
        @Query("page") page: Int,
        @Query("serviceKey") serviceKey: String = BuildConfig.DECODE_API_KEY
    ): Response<List<CenterResponse>>
}