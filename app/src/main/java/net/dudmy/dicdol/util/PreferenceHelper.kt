package net.dudmy.dicdol.util
import android.content.SharedPreferences
import android.preference.PreferenceManager
import net.dudmy.dicdol.DDApplication

/**
 * Created by yujin on 2017. 4. 24..
 */

object PreferenceHelper {

    private val sp: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(DDApplication.context)
    }
}