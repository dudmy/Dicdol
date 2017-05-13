package net.dudmy.dicdol.data.source.remote

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupDataSource.LoadGroupCallback
import net.dudmy.dicdol.data.source.GroupDataSource.LoadGroupsCallback
import net.dudmy.dicdol.services.GroupService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupRemoteDataSource : GroupDataSource {

    override fun getGroups(callback: LoadGroupsCallback) {

        val groupService = GroupService.retrofit.create(GroupService::class.java)
        val call = groupService.getGroupList()

        call.enqueue(object : Callback<List<Group>> {
            override fun onResponse(call: Call<List<Group>>?, response: Response<List<Group>>) {
                if (!response.isSuccessful) {
                    callback.onDataNotAvailable()
                    return
                }

                callback.onGroupsLoaded(response.body())
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

    override fun getGroup(id: Int, callback: LoadGroupCallback) {

        val groupService = GroupService.retrofit.create(GroupService::class.java)
        val call = groupService.getGroup(id)

        call.enqueue(object : Callback<Group> {
            override fun onResponse(call: Call<Group>?, response: Response<Group>) {
                if (response.isSuccessful.not()) {
                    callback.onDataNotAvailable()
                    return
                }

                callback.onGroupLoaded(response.body())
            }

            override fun onFailure(call: Call<Group>?, t: Throwable?) {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun deleteAllGroups() {
        // Not required.
    }

    override fun saveGroup(group: Group) {
        // Not required.
    }
}