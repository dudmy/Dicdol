package net.dudmy.dicdol.data.source

import net.dudmy.dicdol.data.Group

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupDataSource {

    interface LoadGroupsCallback {

        fun onGroupsLoaded(groups: List<Group>)

        fun onDataNotAvailable()
    }

    interface LoadGroupCallback {

        fun onGroupLoaded(group: Group)

        fun onDataNotAvailable()
    }

    fun getGroups(callback: LoadGroupsCallback)

    fun refreshGroups()

    fun getGroup(groupId: String, callback: LoadGroupCallback)

    fun deleteAllGroups()

    fun saveGroup(group: Group)
}