package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupDataSource
import net.dudmy.dicdol.data.source.GroupRepository

class GroupPresenter(private var view: GroupContract.View?,
                     private val adapterModel: GroupAdapterContract.Model,
                     private val adapterView: GroupAdapterContract.View) : GroupContract.Presenter {

    private val repository = GroupRepository

    init {
        adapterView.clickListener = { onItemClick(it) }
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadGroups(type: String?, forceUpdate: Boolean) {

        view!!.setLoadingIndicator(true)

        if (forceUpdate) {
            repository.refreshGroups()
        }

        repository.getGroups(object : GroupDataSource.LoadGroupsCallback {
            override fun onGroupsLoaded(groups: List<Group>) {
                val groupToShow = when (type) {
                    null -> groups
                    "favorite" -> groups.filter { it.favorite }
                    else -> groups.filter { it.type == type }
                }

                adapterModel.addItems(groupToShow)
                adapterView.refresh()

                view?.setLoadingIndicator(false)
            }

            override fun onDataNotAvailable() {
                view?.run {
                    showLoadingGroupsError()
                    setLoadingIndicator(false)
                }
            }
        })
    }

    override fun onItemClick(position: Int) {

        if (position < 0 || position > adapterModel.getSize()) {
            view?.toastOutOfPosition()
            return
        }

        val group = adapterModel.getItem(position)

        view?.startGroupDetailPage(group)
    }

    override fun sortGroups(tag: String) {

        val sortList = if (tag.contains("desc")) {
            adapterModel.getItems().sortedByDescending {
                when (tag) {
                    "name_desc" -> it.name
                    "agency_desc" -> it.agency
                    "debut_desc" -> it.debut
                    else -> it.name
                }
            }
        } else {
            adapterModel.getItems().sortedBy {
                when (tag) {
                    "name" -> it.name
                    "agency" -> it.agency
                    "debut" -> it.debut
                    else -> it.name
                }
            }
        }

        adapterModel.addItems(sortList)
        adapterView.refresh()

        view?.selectCurrentButton(tag)
    }
}