package kr.hs.dgsw.hyeon.domain.repository.center

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.domain.model.Center

interface LocalCenterRepository {
    suspend fun getCenterData(): List<Center>

    suspend fun insertCenter(center: Center)

    suspend fun deleteCenter(center: Center)
}