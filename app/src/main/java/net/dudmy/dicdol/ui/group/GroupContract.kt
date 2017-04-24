package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.BasePresenter
import net.dudmy.dicdol.BaseView

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupContract {

    interface Presenter : BasePresenter {

        fun loadGroups(type: String?, forceUpdate: Boolean)
    }

    interface View : BaseView {

        fun setLoadingIndicator(active: Boolean)

        fun showLoadingGroupsError()
    }
}