package net.dudmy.dicdol.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import net.dudmy.dicdol.R

/**
 * Created by yujin on 2017. 4. 23..
 */

fun ImageView.loadImage(url: String) {

    Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.sample_group)
            .into(this)
}