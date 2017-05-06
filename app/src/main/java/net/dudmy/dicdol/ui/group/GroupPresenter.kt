package net.dudmy.dicdol.ui.group

import android.view.View
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

        view?.startArtistPage(group)
    }

    override fun sortGroups(selectButton: View) {

        val sortList = adapterModel.getItems()
                .sortedBy {
                    when (selectButton.tag) {
                        "agency" -> it.agency
                        "name" -> it.name
                        else -> it.name
                    }
                }

        adapterModel.addItems(sortList)
        adapterView.refresh()

        view?.selectCurrentButton(selectButton)
    }
}