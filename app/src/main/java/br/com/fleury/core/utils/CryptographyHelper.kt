package br.com.grupofleury.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import br.com.fleury.core.BuildConfig.*
import timber.log.Timber
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


object CryptographyHelper {
  fun encryptBytes(
    key: String,
    passwordString: String
  ): HashMap<String, ByteArray>? {
    val map = HashMap<String, ByteArray>()
    try {
      //Random salt for next step
      val random = SecureRandom()
      val salt = ByteArray(256)
      random.nextBytes(salt)

      //PBKDF2 - derive the key from the password, don't use passwords directly
      val passwordChar =
        passwordString.toCharArray() //Turn password into char[] array
      val pbKeySpec = PBEKeySpec(passwordChar, salt, 1324, 256) //1324 iterations
      val secretKeyFactory: SecretKeyFactory =
        SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
      val keyBytes: ByteArray = secretKeyFactory.generateSecret(pbKeySpec).encoded
      val keySpec = SecretKeySpec(keyBytes, "AES")

      //Create initialization vector for AES
      val ivRandom =
        SecureRandom() //not caching previous seeded instance of SecureRandom
      val iv = ByteArray(16)
      ivRandom.nextBytes(iv)
      val ivSpec = IvParameterSpec(iv)

      //Encrypt
      val plainTextBytes = key.toByteArray()
      val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
      val encrypted: ByteArray = cipher.doFinal(plainTextBytes)
      map[SALT] = salt
      map[IV] = iv
      map[ENCRYPTED] = encrypted
    } catch (e: Exception) {
      Timber.e("encryption exception $e")
    }
    return map
  }

  fun decryptData(
    map: HashMap<String, ByteArray>,
    passwordString: String
  ): ByteArray? {
    var decrypted: ByteArray? = null
    try {
      val salt = map[SALT]
      val iv = map[IV]
      val encrypted = map[ENCRYPTED]

      //regenerate key from password
      val passwordChar = passwordString.toCharArray()
      val pbKeySpec =
        PBEKeySpec(passwordChar, salt, 1324, 256)
      val secretKeyFactory =
        SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
      val keyBytes = secretKeyFactory.generateSecret(pbKeySpec).encoded
      val keySpec =
        SecretKeySpec(keyBytes, "AES")

      //Decrypt
      val cipher =
        Cipher.getInstance("AES/CBC/PKCS7Padding")
      val ivSpec = IvParameterSpec(iv)
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
      decrypted = cipher.doFinal(encrypted)
    } catch (e: java.lang.Exception) {
      Timber.e("decryption exception $e")
    }
    return decrypted
  }
}