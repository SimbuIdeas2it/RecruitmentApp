package com.essmart.elsa.data.repository.local.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by abhijeethallur on 05,June,2018
 */
@Singleton
class AppPreferences @Inject constructor(private val sharedPreferences: SharedPreferences, private val gson: Gson) {


    fun save(key: String, o: Any) {
        sharedPreferences.edit()
            .putString(key, gson.toJson(o))
            .apply()
    }

    fun save(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    fun save(key: String, value: Long) {
        sharedPreferences.edit()
            .putLong(key, value)
            .apply()
    }

    operator fun <T> get(key: String, type: Class<T>): T? {
        if (isKeyShared(key))
            return gson.fromJson(getString(key), type)
        return null
    }

    operator fun <T> get(key: String, type: Type): T? {
        if (isKeyShared(key))
            return gson.fromJson(getString(key), type)
        return null
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun isKeyShared(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    fun clearData(key: String) {
        if (isKeyShared(key)) {
            sharedPreferences.edit()
                .remove(key)
                .apply()
        }
    }
}