package com.chw.recruitmentapp.di.module

import android.content.Context
import com.chw.recruitmentapp.application.RecruitmentApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return RecruitmentApp.instance
    }
}