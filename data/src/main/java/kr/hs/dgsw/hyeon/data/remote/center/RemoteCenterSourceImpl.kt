package kr.hs.dgsw.hyeon.data.remote.center

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.hyeon.data.datasource.center.RemoteCenterDataSource
import kr.hs.dgsw.hyeon.data.mapper.toModel
import kr.hs.dgsw.hyeon.data.service.CenterService
import kr.hs.dgsw.hyeon.domain.model.Center
import javax.inject.Inject

class RemoteCenterSourceImpl @Inject constructor(
    private val service: CenterService
): RemoteCenterDataSource {
    override fun getCenterData(page: Int): Flow<List<Center>> = flow {
        val centerItem = service.getCenterDataByRemote(page).data.map {
            it.toModel()
        }
        emit(centerItem)
    }
}