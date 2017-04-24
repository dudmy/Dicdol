package net.dudmy.dicdol.data.source.remote

import com.google.gson.Gson
import net.dudmy.dicdol.DDApplication
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.GroupJson
import net.dudmy.dicdol.data.source.GroupRepository
import net.dudmy.dicdol.util.PreferenceHelper
import java.io.IOException

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupRemoteDataSource : GroupDataSource {

    override fun getGroups(callback: GroupRepository.LoadGroupsCallback) {

        val json = getStringAssets("group.json")

        if (json == null) {
            callback.onDataNotAvailable()
        } else {
            val groupJson = Gson().fromJson(json, GroupJson::class.java)

            if (groupJson.items!!.isEmpty()) {
                callback.onDataNotAvailable()
            } else {
                callback.onGroupsLoaded(groupJson.items!!)
            }

            PreferenceHelper.saveGroup(json)
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
}