package kr.hs.dgsw.hyeon.vaccination_application.module.center

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.data.service.CenterService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterApiModule {

    @Provides
    @Singleton
    fun provideCenterService(retrofit: Retrofit): CenterService =
        retrofit.create(CenterService::class.java)
}