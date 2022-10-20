package kr.hs.dgsw.hyeon.data.repository.center.remote

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.hyeon.data.datasource.center.RemoteCenterDataSource
import kr.hs.dgsw.hyeon.domain.model.Center
import kr.hs.dgsw.hyeon.domain.repository.center.RemoteCenterRepository
import javax.inject.Inject

class RemoteCenterRepoImpl @Inject constructor(
    private val source: RemoteCenterDataSource
): RemoteCenterRepository {
    override fun getRemoteCenterData(page: Int): Flow<List<Center>> =
        source.getCenterData(page)

}