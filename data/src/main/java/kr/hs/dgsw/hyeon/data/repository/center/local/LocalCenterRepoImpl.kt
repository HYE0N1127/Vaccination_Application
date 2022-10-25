package kr.hs.dgsw.hyeon.data.repository.center.local

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.data.datasource.center.LocalCenterDataSource
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository
import javax.inject.Inject

class LocalCenterRepoImpl @Inject constructor(
    private val source: LocalCenterDataSource
): LocalCenterRepository {

    override suspend fun getCenterData(): List<Center> =
        source.getCenterDataByRemote()

    override suspend fun insertCenter(center: Center) =
        source.insertCenterData(center)

    override suspend fun deleteCenter(center: Center) =
        source.deleteCenterData(center)
}