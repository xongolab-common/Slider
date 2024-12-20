package com.example.slider

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco


class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun attachBaseContext(base: Context) {
        instance = this
        super.attachBaseContext(base)
        // Perform any locale setup or other configuration here if necessary
    }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}