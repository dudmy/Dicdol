package net.dudmy.dicdol.ui.artist

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_pager.view.*
import net.dudmy.dicdol.R
import net.dudmy.dicdol.util.loadImage

/**
 * Created by yujin on 2017. 6. 11..
 */

class ArtistPagerAdapter(context: Context, val images: List<String>) : PagerAdapter() {

    private val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = images.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view === `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = inflater.inflate(R.layout.item_pager, container, false)

        itemView.iv_pager.loadImage(images[position])

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as View)
    }
}