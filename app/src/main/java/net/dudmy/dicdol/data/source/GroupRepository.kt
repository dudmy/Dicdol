package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.local.GroupLocalDataSource
import net.dudmy.dicdol.data.source.remote.GroupRemoteDataSource

/**
 * Created by yujin on 2017. 4. 23..
 */

class GroupRepository : GroupDataSource {

    private val remoteDataSource by lazy { GroupRemoteDataSource() }

    private val localDataSource by lazy { GroupLocalDataSource() }

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    private var cachedGroups: MutableMap<String, Group>? = null

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private var cacheIsDirty = false

    override fun getGroups(callback: LoadGroupsCallback) {

        // Respond immediately with cache if available and not dirty
        if (cachedGroups != null && !cacheIsDirty) {
            callback.onGroupsLoaded(ArrayList(cachedGroups!!.values))
            return
        }

        if (cacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
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
        for (group in groups) {
            cachedGroups!!.put(group.id, group)
        }
        cacheIsDirty = false
    }

    override fun refreshGroups() {
        cacheIsDirty = true
    }

    interface LoadGroupsCallback {

        fun onGroupsLoaded(groups: List<Group>)

        fun onDataNotAvailable()
    }
}