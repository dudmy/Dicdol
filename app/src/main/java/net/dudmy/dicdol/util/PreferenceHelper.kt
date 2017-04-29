package net.dudmy.dicdol.util
import android.content.SharedPreferences
import android.preference.PreferenceManager
import net.dudmy.dicdol.DDApplication

/**
 * Created by yujin on 2017. 4. 24..
 */

object PreferenceHelper {

    private val pref_groups = "pref_groups"
    private val pref_group = "pref_group_"

    private val sp: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(DDApplication.context)
    }

    fun saveGroups(group: String) {
        val editor = sp.edit()
        editor.putString(pref_groups, group)
        editor.apply()
    }

    fun loadGroups(): String? {
        return sp.getString(pref_groups, null)
    }

    fun saveGroup(groupId: String, group: String) {
        val editor = sp.edit()
        editor.putString(pref_group + groupId, group)
        editor.apply()
    }

    fun loadGroup(groupId: String): String? {
        return sp.getString(pref_group + groupId, null)
    }
}