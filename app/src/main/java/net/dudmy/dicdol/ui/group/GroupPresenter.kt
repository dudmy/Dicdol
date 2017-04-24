package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupRepository

class GroupPresenter(private var view: GroupContract.View?,
                     private val adapterModel: GroupAdapterContract.Model,
                     private val adapterView: GroupAdapterContract.View,
                     private val repository: GroupRepository) : GroupContract.Presenter {

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

        repository.getGroups(object : GroupRepository.LoadGroupsCallback {
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
}