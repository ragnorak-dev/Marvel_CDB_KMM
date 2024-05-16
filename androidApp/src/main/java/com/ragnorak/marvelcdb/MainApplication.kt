package com.ragnorak.marvelcdb

import android.app.Application
import com.ragnorak.marvelcdb.di.androidModule
import com.ragnorak.marvelcdb.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(commonModule, androidModule)
        }
    }
}