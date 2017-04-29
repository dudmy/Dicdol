package net.dudmy.dicdol.data.source.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.dudmy.dicdol.DDApplication
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupRepository
import net.dudmy.dicdol.util.PreferenceHelper
import java.io.IOException

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupLocalDataSource : GroupDataSource {

    override fun getGroups(callback: GroupRepository.LoadGroupsCallback) {

        var json: String? = PreferenceHelper.loadGroups()

        if (json == null) {
            json = getStringAssets("group/group.json")
        }

        if (json == null) {
            callback.onDataNotAvailable()
            return
        }

        val groupList: List<Group> =
                Gson().fromJson(
                        json,
                        object : TypeToken<ArrayList<Group>>(){}.type)

        when {
            groupList.isEmpty() -> callback.onDataNotAvailable()
            else -> callback.onGroupsLoaded(groupList)
        }
    }

    private fun getStringAssets(file: String): String? {
        try {
            val inputStream = DDApplication.context.assets.open(file)
            val buffer = ByteArray(inputStream.available())

            inputStream.read(buffer)
            inputStream.close()

            return String(buffer)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun refreshGroups() {
        // Not required because the {@link GroupRepository} handles the logic of refreshing the
        // groups from all the available data sources.
    }

    override fun getGroup(groupId: String, callback: GroupRepository.LoadGroupCallback) {

        var json: String? = PreferenceHelper.loadGroup(groupId)

        if (json == null) {
            json = getStringAssets("group/$groupId.json")
        }

        if (json == null) {
            callback.onDataNotAvailable()
            return
        }

        val group = Gson().fromJson(json, Group::class.java)
        callback.onGroupLoaded(group)
    }
}