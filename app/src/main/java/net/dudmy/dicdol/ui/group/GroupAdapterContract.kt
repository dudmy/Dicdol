package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.data.Group

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupAdapterContract {

    interface Model {

        fun getItem(position: Int): Group

        fun addItems(groups: List<Group>)
    }

    interface View {

        fun refresh()
    }
}