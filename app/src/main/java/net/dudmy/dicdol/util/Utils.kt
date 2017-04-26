package net.dudmy.dicdol.util

import android.content.Context
import android.nfc.FormatException
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import net.dudmy.dicdol.R

/**
 * Created by yujin on 2017. 4. 23..
 */

fun ImageView.loadImage(url: String) {

    Glide.with(context)
            .load(assetsPrefix + url)
            .centerCrop()
            .placeholder(R.drawable.sample_group)
            .into(this)
}

fun Context.toast(message: Any) {

    when (message) {
        is Int -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        is String -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        else -> throw FormatException("Message must be an int or a string. Input=$message")
    }
}