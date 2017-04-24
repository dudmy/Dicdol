package net.dudmy.dicdol.data.source

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupDataSource {

    fun getGroups(callback: GroupRepository.LoadGroupsCallback)

    fun refreshGroups()
}