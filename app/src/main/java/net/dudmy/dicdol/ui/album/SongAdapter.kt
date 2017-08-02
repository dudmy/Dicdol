package net.dudmy.dicdol.ui.album

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_song.view.*
import net.dudmy.dicdol.R

/**
 * Created by yujin on 2017. 8. 2..
 */

class SongAdapter : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var songs: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_song, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        songs?.let {
            holder.tvTitle.text = it[position]
        }
    }

    override fun getItemCount(): Int = songs?.size ?: 0

    fun addItems(songs: List<String>?) {
        this.songs = songs
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tv_title
    }
}