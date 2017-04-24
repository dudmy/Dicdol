package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.local.GroupLocalDataSource
import net.dudmy.dicdol.data.source.remote.GroupRemoteDataSource
import net.dudmy.dicdol.util.PreferenceHelper

/**
 * Created by yujin on 2017. 4. 23..
 */

class GroupRepository : GroupDataSource {

    private val remoteDataSource by lazy { GroupRemoteDataSource() }

    private val localDataSource by lazy { GroupLocalDataSource() }

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    internal var cachedGroups: MutableMap<String, Group>? = null

    override fun getGroups(callback: LoadGroupsCallback) {

        // Respond immediately with cache if available
        if (cachedGroups != null) {
            callback.onGroupsLoaded(ArrayList(cachedGroups!!.values))
            return
        }

        if (PreferenceHelper.loadGroup() == null) {
            // If the cache is null we need to fetch new data from the network.
            getGroupsFromRemoteDataSource(callback)
        } else {
            localDataSource.getGroups(object : LoadGroupsCallback {
                override fun onGroupsLoaded(groups: List<Group>) {
                    refreshCache(groups)
                    callback.onGroupsLoaded(ArrayList(cachedGroups!!.values))
                }

                override fun onDataNotAvailable() {
                    getGroupsFromRemoteDataSource(callback)
                }
            })
        }
    }

    private fun getGroupsFromRemoteDataSource(callback: LoadGroupsCallback) {
        remoteDataSource.getGroups(object : LoadGroupsCallback {
            override fun onGroupsLoaded(groups: List<Group>) {
                refreshCache(groups)
                callback.onGroupsLoaded(ArrayList(cachedGroups!!.values))
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    private fun refreshCache(groups: List<Group>) {
        if (cachedGroups == null) {
            cachedGroups = LinkedHashMap<String, Group>()
        }
        cachedGroups!!.clear()
        for (task in groups) {
            cachedGroups!!.put(task.id, task)
        }
    }

    interface LoadGroupsCallback {

        fun onGroupsLoaded(groups: List<Group>)

        fun onDataNotAvailable()
    }
}