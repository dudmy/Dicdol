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

        fun sortGroups(tag: String)
    }

    interface View : BaseView {

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingGroupsError()

        fun toastOutOfPosition()

        fun startGroupDetailPage(group: Group)

        fun selectCurrentButton(tag: String)

        fun clearCache()
    }
}