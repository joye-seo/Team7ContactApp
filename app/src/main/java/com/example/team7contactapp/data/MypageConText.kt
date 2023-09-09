package com.example.team7contactapp.data

import android.app.Application
import android.content.Context

class MypageConText : Application() {

    companion object {
        lateinit var globalContext: Context
        const val PREF_MYPAGE = "mypage_prefs"
        const val KEY_MYNAME = "myname"
        const val KEY_MYIMAGE_PATH = "key_myimage_path"
        const val KEY_MYNUMBER = "mynumber"
        const val KEY_MYEMAIL = "myemail"
        const val KEY_MYADDRESS = "myaddress"
        const val KEY_MYBIRTHDAY = "mybirthday"
        const val KEY_MYMEMO = "mymemo"
    }

    override fun onCreate() {
        super.onCreate()
        globalContext = this
    }
}