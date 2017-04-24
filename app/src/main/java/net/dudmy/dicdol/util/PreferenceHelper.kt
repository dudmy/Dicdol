package net.dudmy.dicdol.util
import android.content.SharedPreferences
import android.preference.PreferenceManager
import net.dudmy.dicdol.DDApplication

/**
 * Created by yujin on 2017. 4. 24..
 */

object PreferenceHelper {

    private val pref_group = "pref_group"

    private val sp: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(DDApplication.context)
    }

    fun saveGroup(group: String) {
        val editor = sp.edit()
        editor.putString(pref_group, group)
        editor.apply()
    }

    fun loadGroup(): String? {
        return sp.getString(pref_group, null)
    }
}