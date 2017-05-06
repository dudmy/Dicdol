package net.dudmy.dicdol.data.source.local

import io.realm.Realm
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupDataSource.LoadGroupCallback
import net.dudmy.dicdol.data.source.GroupDataSource.LoadGroupsCallback

/**
 * Created by yujin on 2017. 4. 24..
 */

class GroupLocalDataSource : GroupDataSource {

    override fun getGroups(callback: LoadGroupsCallback) {
        var groups: List<Group>? = null

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val result = it.where(Group::class.java)
                        .findAll()

                groups = it.copyFromRealm(result)
            }
        }

        if (groups?.isEmpty() ?: true) {
            callback.onDataNotAvailable()
        } else {
            callback.onGroupsLoaded(groups!!)
        }
    }

    override fun refreshGroups() {
        // Not required because the {@link GroupRepository} handles the logic of refreshing the
        // groups from all the available data sources.
    }

    override fun getGroup(groupId: String, callback: LoadGroupCallback) {
        var group: Group? = null

        Realm.getDefaultInstance().use {
            it.executeTransaction {
                val result = it.where(Group::class.java)
                        .equalTo("id", groupId)
                        .findFirst()

                group = it.copyFromRealm(result)
            }
        }

        if (group?.needRefresh() ?: true) {
            callback.onDataNotAvailable()
        } else {
            callback.onGroupLoaded(group!!)
        }
    }

    override fun deleteAllGroups() {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.where(Group::class.java)
                        .findAll()
                        .deleteAllFromRealm()
            }
        }
    }

    override fun saveGroup(group: Group) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.copyToRealmOrUpdate(group)
            }
        }
    }
}