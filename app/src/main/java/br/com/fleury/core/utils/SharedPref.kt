package br.com.grupofleury.core.utils

import android.content.Context
import android.content.SharedPreferences
import br.com.fleury.core.BuildConfig.SHARED_PREFS

class SharedPref(val context: Context) {

  var sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

  fun getBoolean(key: String) = sharedPreferences.getBoolean(key, false)

  fun getString(key: String) = sharedPreferences.getString(key, "")

  fun getDouble(key: String): Double {
    val res = sharedPreferences.getFloat(key, 0f)
    return res.toDouble()
  }

  fun getInt(key: String) = sharedPreferences.getInt(key, 0)

  fun getLong(key: String) = sharedPreferences.getLong(key, 0L)

  fun put(key: String, text: Boolean) = sharedPreferences.edit().putBoolean(key, text).apply()

  fun put(key: String, text: String) = sharedPreferences.edit().putString(key, text).apply()

  fun put(key: String, text: Double) = sharedPreferences.edit().putFloat(key, text.toFloat()).apply()

  fun put(key: String, text: Int) = sharedPreferences.edit().putInt(key, text).apply()

  fun put(key: String, text: Long) = sharedPreferences.edit().putLong(key, text).apply()

  fun clear() = sharedPreferences.edit().clear().commit()

}