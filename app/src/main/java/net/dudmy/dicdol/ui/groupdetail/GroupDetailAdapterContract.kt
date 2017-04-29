package net.dudmy.dicdol.ui.groupdetail

/**
 * Created by yujin on 2017. 4. 29..
 */
interface GroupDetailAdapterContract {

    interface Model {

        fun getItem(position: Int): Any

        fun addItems(items: List<Any>?)
    }

    interface View {

        fun refresh()

        var clickListener: ((Int) -> Unit)?
    }
}