package kr.hs.dgsw.hyeon.vaccination_application.module.center

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.data.datasource.center.LocalCenterDataSource
import kr.hs.dgsw.hyeon.data.datasource.center.RemoteCenterDataSource
import kr.hs.dgsw.hyeon.data.local.dao.CenterDao
import kr.hs.dgsw.hyeon.data.remote.center.LocalCenterSourceImpl
import kr.hs.dgsw.hyeon.data.remote.center.RemoteCenterSourceImpl
import kr.hs.dgsw.hyeon.data.service.CenterService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterDataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: CenterService): RemoteCenterDataSource =
        RemoteCenterSourceImpl(service)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: CenterDao): LocalCenterDataSource =
        LocalCenterSourceImpl(dao)
}