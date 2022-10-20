package kr.hs.dgsw.hyeon.vaccination_application.module.center.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.hs.dgsw.hyeon.data.local.CenterDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CenterDataBaseModule {

    @Provides
    @Singleton
    fun provideCenterDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CenterDataBase::class.java, "center_database")
            .fallbackToDestructiveMigration()
            .build()
}