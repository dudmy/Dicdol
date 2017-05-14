package net.dudmy.dicdol.ui.groupdetail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_simple_grid.view.*
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Album
import net.dudmy.dicdol.data.Artist
import net.dudmy.dicdol.util.loadRoundImage

/**
 * Created by yujin on 2017. 4. 29..
 */

class GroupDetailAdapter : RecyclerView.Adapter<GroupDetailAdapter.ViewHolder>(), GroupDetailAdapterContract.View, GroupDetailAdapterContract.Model {

    private var items: List<Any>? = null

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_simple_grid, parent, false)
        return ViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = getItem(position)

        when (item) {
            is Artist -> {
                holder.ivGrid.loadRoundImage(item.image)
                holder.tvGrid.text = item.name
            }
            is Album -> {
                holder.ivGrid.loadRoundImage(item.image)
                holder.tvGrid.text = item.name
            }
        }
    }

    override fun getItem(position: Int): Any = items!![position]

    override fun addItems(items: List<Any>?) {
        this.items = items
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override var clickListener: ((Int) -> Unit)? = null

    class ViewHolder(itemView: View, val clickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        val ivGrid: ImageView = itemView.iv_simple_gird
        val tvGrid: TextView = itemView.tv_simple_gird

        init {
            itemView.setOnClickListener { clickListener?.invoke(adapterPosition) }
        }
    }
}