package br.com.grupofleury.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import br.com.fleury.core.BuildConfig.*
import br.com.fleury.core.utils.SecureData
import br.com.grupofleury.core.utils.CryptographyHelper.decryptData
import br.com.grupofleury.core.utils.CryptographyHelper.encryptBytes
import com.google.gson.Gson
import timber.log.Timber

class SharedPref(val context: Context, val gson: Gson) {

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


  fun putSecureString(key: String, value: String){
    try {
      val map: HashMap<String, ByteArray>? = encryptBytes(value, UserSuppliedPassword)
      map?.let { it ->
        val secureString = SecureData(it[ENCRYPTED], it[IV], it[SALT])
        val keyBase64String: String = Base64.encodeToString(key.toByteArray(), Base64.NO_WRAP)
        val valueBase64String: String =
          Base64.encodeToString(gson.toJson(secureString).toByteArray(), Base64.NO_WRAP)
        sharedPreferences.edit().putString(keyBase64String, valueBase64String).apply()
      }
    } catch (e: java.lang.Exception) {
      Timber.e("putSecureString Exception $e")
    }
  }

  fun getSecureString(key: String): String {
    var decryptedString = ""
    try {
      val keyBase64: String = Base64.encodeToString(key.toByteArray(), Base64.NO_WRAP)
      val valueBase64 = sharedPreferences.getString(keyBase64, "")
      val encryptedString = String(Base64.decode(valueBase64, Base64.NO_WRAP))

      val encryptedData = gson.fromJson(encryptedString, SecureData::class.java)

      encryptedData.let {
        val mapToDecrypt = HashMap<String, ByteArray>()
        it.value?.let { value -> mapToDecrypt[ENCRYPTED] = value }
        it.iv?.let { iv -> mapToDecrypt[IV] = iv }
        it.salt?.let { salt -> mapToDecrypt[SALT] = salt }

        val decrypted: ByteArray? = decryptData(mapToDecrypt, UserSuppliedPassword)
        if (decrypted != null) {
          decryptedString = String(decrypted)
        }
      }
    } catch (e: java.lang.Exception) {
      Timber.e("getSecureString Exception $e")
    }

    return decryptedString
  }
}