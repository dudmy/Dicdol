package net.dudmy.dicdol

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import net.dudmy.dicdol.util.PreferenceHelper

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

        Realm.init(context)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build())

        PreferenceHelper.PrefDelegate.initialize(context)
    }
}