package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.data.source.GroupRepository

class GroupPresenter(private var view: GroupContract.View?,
                     private val adapterModel: GroupAdapterContract.Model,
                     private val adapterView: GroupAdapterContract.View,
                     private val repository: GroupRepository) : GroupContract.Presenter {

    override fun detachView() {
        this.view = null
    }

    override fun loadGroups(type: String?) {

        view!!.setLoadingIndicator(true)

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
}