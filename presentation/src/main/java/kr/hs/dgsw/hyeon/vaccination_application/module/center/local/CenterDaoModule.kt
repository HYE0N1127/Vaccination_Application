package kr.hs.dgsw.hyeon.vaccination_application.module.center.local

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.data.local.CenterDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterDaoModule {

    @Provides
    @Singleton
    fun provideCenterDao(database: CenterDataBase) = database.centerDao()
}