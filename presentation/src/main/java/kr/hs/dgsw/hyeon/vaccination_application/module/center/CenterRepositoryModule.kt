package kr.hs.dgsw.hyeon.vaccination_application.module.center

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.data.datasource.center.LocalCenterDataSource
import kr.hs.dgsw.hyeon.data.datasource.center.RemoteCenterDataSource
import kr.hs.dgsw.hyeon.data.repository.center.local.LocalCenterRepoImpl
import kr.hs.dgsw.hyeon.data.repository.center.remote.RemoteCenterRepoImpl
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository
import kr.hs.dgsw.hyeon.domain.repository.center.RemoteCenterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterRepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(datasource: RemoteCenterDataSource): RemoteCenterRepository =
        RemoteCenterRepoImpl(datasource)

    @Provides
    @Singleton
    fun provideLocalRepository(datasource: LocalCenterDataSource): LocalCenterRepository =
        LocalCenterRepoImpl(datasource)
}