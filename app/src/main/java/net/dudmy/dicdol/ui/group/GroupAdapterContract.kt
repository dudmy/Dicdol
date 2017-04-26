package net.dudmy.dicdol.ui.group

import net.dudmy.dicdol.data.Group

/**
 * Created by yujin on 2017. 4. 23..
 */

interface GroupAdapterContract {

    interface Model {

        fun getItems(): List<Group>

        fun getItem(position: Int): Group

        fun addItems(groups: List<Group>)

        fun getSize(): Int
    }

    interface View {

        fun refresh()

        var clickListener: ((Int) -> Unit)?
    }
}