package net.dudmy.dicdol.ui.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by yujin on 2017. 4. 29..
 */

class GridOffsetDecoration(private val divSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = divSize
        outRect.bottom = divSize
        outRect.left = divSize
        outRect.right = divSize
    }
}