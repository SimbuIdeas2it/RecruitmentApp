package com.chw.recruitmentapp.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.chw.recruitmentapp.application.RecruitmentApp
import com.chw.recruitmentapp.data.api.ApiService
import com.chw.recruitmentapp.data.repository.AppRepository
import com.chw.recruitmentapp.data.repository.local.LocalRepository
import com.chw.recruitmentapp.data.repository.remote.RemoteRepository
import com.essmart.elsa.data.repository.local.preferences.AppPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(RecruitmentApp.instance.applicationContext)
    }

    @Provides
    @Singleton
    fun provideAppPreferences(sharedPreferences: SharedPreferences, gson: Gson): AppPreferences {
        return AppPreferences(sharedPreferences, gson)
    }

    @Singleton
    @Provides
    fun provideElsaRemoteRepository(apiService: ApiService): RemoteRepository {
        return RemoteRepository(apiService)
    }

//    @Singleton
//    @Provides
//    fun provideElsaLocalRepository(
//        elsaAppPreferences: AppPreferences,
//        gson: Gson,
//        elsaDatabase: ElsaDatabase
//    ): ElsaLocalRepository {
//        return ElsaLocalRepository(elsaAppPreferences, gson, elsaDatabase)
//    }

    @Singleton
    @Provides
    fun provideElsaRepository(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): AppRepository {
        return AppRepository(remoteRepository, localRepository)
    }

}