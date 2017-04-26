package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.BasePresenter
import net.dudmy.dicdol.BaseView
import net.dudmy.dicdol.data.Group

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupContract {

    interface Presenter : BasePresenter {

        fun loadGroups(type: String?, forceUpdate: Boolean)

        fun onItemClick(position: Int)

        fun sortGroups(selectButton: android.view.View)
    }

    interface View : BaseView {

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingGroupsError()

        fun toastOutOfPosition()

        fun startArtistPage(group: Group)

        fun selectCurrentButton(selectButton: android.view.View)
    }
}