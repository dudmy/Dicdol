package net.dudmy.dicdol.data.source.remote

import com.google.gson.Gson
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupRepository
import net.dudmy.dicdol.services.GroupService
import net.dudmy.dicdol.util.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupRemoteDataSource : GroupDataSource {

    override fun getGroups(callback: GroupRepository.LoadGroupsCallback) {

        val groupService = GroupService.retrofit.create(GroupService::class.java)
        val call = groupService.getGroupList()

        call.enqueue(object : Callback<List<Group>> {
            override fun onResponse(call: Call<List<Group>>?, response: Response<List<Group>>?) {
                response?.let {
                    if (!it.isSuccessful) {
                        callback.onDataNotAvailable()
                        return
                    }

                    callback.onGroupsLoaded(it.body())

                    // Save to local data source.
                    PreferenceHelper.saveGroup(Gson().toJson(it.body()))
                }
            }

            override fun onFailure(call: Call<List<Group>>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun refreshGroups() {
        // Not required because the {@link GroupRepository} handles the logic of refreshing the
        // groups from all the available data sources.
    }
}