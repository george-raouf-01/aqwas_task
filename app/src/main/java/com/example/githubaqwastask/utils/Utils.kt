package com.example.githubaqwastask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun isInternetConnected(context: Context): Boolean {

    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnected == true
}