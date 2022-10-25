package kr.hs.dgsw.hyeon.domain.repository.center

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center

interface RemoteCenterRepository {

    fun getRemoteCenterData(page: Int): Flow<List<Center>>
}