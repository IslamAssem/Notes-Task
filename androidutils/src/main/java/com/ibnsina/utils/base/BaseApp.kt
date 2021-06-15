package com.ibnsina.utils.base


import android.app.Application
import android.content.Context
import com.ibnsina.utils.LocalHelper
import com.ibnsina.utils.SharedPreferencesHelper

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesHelper.mContext = this
    }
    override fun attachBaseContext(base: Context?) {
        SharedPreferencesHelper.mContext = base
        super.attachBaseContext(base?.let { LocalHelper.onAttachApp(it) })
    }
}