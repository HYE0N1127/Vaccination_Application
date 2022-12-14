package kr.hs.dgsw.hyeon.data.remote.center

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.hs.dgsw.hyeon.data.datasource.center.LocalCenterDataSource
import kr.hs.dgsw.hyeon.data.local.dao.CenterDao
import kr.hs.dgsw.hyeon.data.mapper.toEntity
import kr.hs.dgsw.hyeon.data.mapper.toModel
import kr.hs.dgsw.hyeon.domain.model.Center
import javax.inject.Inject

class LocalCenterSourceImpl @Inject constructor(
    private val dao: CenterDao
) : LocalCenterDataSource {

    override suspend fun getCenterDataByRemote(): List<Center> {
        return dao.getCenterData().map {
            it.toModel()
        }
    }

    override suspend fun insertCenterData(center: Center) {
        return dao.insertCenter(center.toEntity())
    }

    override suspend fun deleteCenterData(center: Center) {
        return dao.deleteCenter(center.toEntity())
    }
}