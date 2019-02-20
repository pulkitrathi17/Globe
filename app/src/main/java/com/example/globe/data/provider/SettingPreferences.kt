package com.example.globe.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object SettingPreferences{
    private lateinit var preferences: SharedPreferences
    private const val LANGUAGE = "LANGUAGE"
    private const val LOCATION = "LOCATION"

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }

    fun getLanguage(): String {
        val selectedLanguage = preferences.getString(LANGUAGE, "en")
        return selectedLanguage!!
    }

    fun getLocation(): String {
        val selectedLocation = preferences.getString(LOCATION, "in")
        return selectedLocation!!
    }
}