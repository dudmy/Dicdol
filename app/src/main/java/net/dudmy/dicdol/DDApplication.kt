package net.dudmy.dicdol

import android.app.Application
import android.content.Context

/**
 * Created by yujin on 2017. 4. 24..
 */

class DDApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
    }
}