package net.dudmy.dicdol.data.source.local

import com.google.gson.Gson
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.GroupJson
import net.dudmy.dicdol.data.source.GroupRepository
import net.dudmy.dicdol.util.PreferenceHelper

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupLocalDataSource : GroupDataSource {

    override fun getGroups(callback: GroupRepository.LoadGroupsCallback) {

        val json = PreferenceHelper.loadGroup()

        val groupJson = Gson().fromJson(json, GroupJson::class.java)

        if (groupJson.items!!.isEmpty()) {
            callback.onDataNotAvailable()
        } else {
            callback.onGroupsLoaded(groupJson.items!!)
        }
    }

    override fun refreshGroups() {
        // Not required because the {@link GroupRepository} handles the logic of refreshing the
        // groups from all the available data sources.
    }
}