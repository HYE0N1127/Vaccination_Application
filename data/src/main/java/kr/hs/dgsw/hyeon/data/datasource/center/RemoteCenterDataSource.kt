package kr.hs.dgsw.hyeon.data.datasource.center

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center

interface RemoteCenterDataSource {
    fun getCenterData(page: Int): Flow<List<Center>>
}