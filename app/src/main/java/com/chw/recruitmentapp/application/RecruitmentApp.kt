package com.chw.recruitmentapp.application

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecruitmentApp: Application() {

    companion object {
        operator fun get(context: Context): RecruitmentApp = context.applicationContext as RecruitmentApp
        lateinit var instance: RecruitmentApp
    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        instance = this@RecruitmentApp
    }
}