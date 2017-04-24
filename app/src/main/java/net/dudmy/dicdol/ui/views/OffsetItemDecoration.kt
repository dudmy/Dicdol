package net.dudmy.dicdol.ui.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by yujin on 2017. 4. 23..
 */

class OffsetItemDecoration(private val divHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = divHeight
        }

        outRect.bottom = divHeight
    }
}