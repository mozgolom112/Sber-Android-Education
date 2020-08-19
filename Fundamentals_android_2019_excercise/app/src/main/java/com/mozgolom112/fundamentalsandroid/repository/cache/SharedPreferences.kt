package com.mozgolom112.fundamentalsandroid.repository.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREF_KEY_LAST_CACHE = "last_cache"
private const val PREF_PACKAGE_NAME = "com.mozgolom112.fundamentalsandroid"

class SharedPreferencesImpl(context: Context) : SharedPreferencesInterface {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        PREF_PACKAGE_NAME,
        Context.MODE_PRIVATE
    )

    override var lastCacheTime: Long
        get() = prefs.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = prefs.edit { putLong(PREF_KEY_LAST_CACHE, lastCache) }
}