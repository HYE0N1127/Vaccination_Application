package kr.hs.dgsw.hyeon.vaccination_application.module.center

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.domain.repository.center.LocalCenterRepository
import kr.hs.dgsw.hyeon.domain.repository.center.RemoteCenterRepository
import kr.hs.dgsw.hyeon.domain.usecase.center.local.DeleteCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.local.GetLocalCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.local.InsertCenterDataUseCase
import kr.hs.dgsw.hyeon.domain.usecase.center.remote.GetRemoteCenterDataUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterUseCaseModule {

    @Provides
    @Singleton
    fun provideGetRemoteCenterUseCase(remoteRepository: RemoteCenterRepository) =
        GetRemoteCenterDataUseCase(remoteRepository)

    @Provides
    @Singleton
    fun provideGetLocalCenterUseCase(localRepository: LocalCenterRepository) =
        GetLocalCenterDataUseCase(localRepository)

    @Provides
    @Singleton
    fun provideInsertCenterUseCase(localRepository: LocalCenterRepository) =
        InsertCenterDataUseCase(localRepository)

    @Provides
    @Singleton
    fun provideDeleteCenterUseCase(localRepository: LocalCenterRepository) =
        DeleteCenterDataUseCase(localRepository)

}