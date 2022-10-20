package kr.hs.dgsw.hyeon.data.datasource.center

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center

interface LocalCenterDataSource {
    fun getCenterDataByRemote() : Flow<List<Center>>

    suspend fun insertCenterData(center: Center)

    suspend fun deleteCenterData(center: Center)
}