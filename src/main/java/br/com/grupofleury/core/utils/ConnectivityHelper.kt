package br.com.grupofleury.core.utils

import android.content.Context
import android.net.ConnectivityManager


object ConnectivityHelper {
  fun on(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
  }
}