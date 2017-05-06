package net.dudmy.dicdol.util

import android.content.Context
import android.nfc.FormatException
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.CropCircleTransformation
import net.dudmy.dicdol.DDApplication
import net.dudmy.dicdol.R
import java.io.IOException

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

fun ImageView.loadRoundImage(url: String) {

    Glide.with(context)
            .load(assetsPrefix + url)
            .bitmapTransform(CropCircleTransformation(context))
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

fun getStringAssets(file: String): String? {
    try {
        val inputStream = DDApplication.context.assets.open(file)
        val buffer = ByteArray(inputStream.available())

        inputStream.read(buffer)
        inputStream.close()

        return String(buffer)

    } catch (e: IOException) {
        e.printStackTrace()
    }

    return null
}