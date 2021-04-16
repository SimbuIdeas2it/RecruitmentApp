package com.chw.recruitmentapp.data.repository.local

import com.essmart.elsa.data.repository.local.preferences.AppPreferences
import com.essmart.elsa.data.repository.local.preferences.PreferencesConstants
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val preferences: AppPreferences,
    private val gson: Gson
){
    fun saveAccessToken(it: String) {
        preferences.save(PreferencesConstants.ACCESS_TOKEN, it)
    }

    fun getAccessToken(): String? {
        return preferences.getString(PreferencesConstants.ACCESS_TOKEN)
    }

    fun clearUserData() {
        preferences.clearData(PreferencesConstants.ACCESS_TOKEN)
    }

}