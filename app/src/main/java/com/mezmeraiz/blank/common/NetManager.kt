package com.mezmeraiz.blank.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetManager @Inject constructor(private var applicationContext: Application) {

    val isConnectedToInternet: Boolean
        get() {
            val conManager = applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }

}