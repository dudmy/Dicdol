package net.dudmy.dicdol.ui.group

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_group.view.*
import net.dudmy.dicdol.R
import net.dudmy.dicdol.data.Group
import net.dudmy.dicdol.util.loadImage

/**
 * Created by yujin on 2017. 4. 23..
 */

class GroupAdapter : RecyclerView.Adapter<GroupAdapter.ViewHolder>(), GroupAdapterContract.Model, GroupAdapterContract.View {

    private var groups: List<Group>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_group, parent, false)
        return ViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position).let {
            holder.tvName.text = it.name
            holder.tvAgency.text = it.agency
            holder.tvType.text = it.getTypeStr()
            holder.ivGroup.loadImage(it.image)
        }
    }

    override fun getItemCount(): Int = groups?.size ?: 0

    override fun getSize(): Int = itemCount

    override fun getItems(): List<Group> = groups!!

    override fun getItem(position: Int) = groups!![position]

    override fun addItems(groups: List<Group>) {
        this.groups = groups
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override var clickListener: ((Int) -> Unit)? = null

    class ViewHolder(itemView: View, val clickListener: ((Int) -> Unit)?) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.tv_name
        val tvAgency: TextView = itemView.tv_agency
        val tvType: TextView = itemView.tv_type
        val ivGroup: ImageView = itemView.iv_group

        init {
            itemView.setOnClickListener {
                clickListener?.invoke(adapterPosition)
            }
        }
    }
}