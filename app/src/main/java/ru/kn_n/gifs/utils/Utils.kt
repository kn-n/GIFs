package ru.kn_n.gifs.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

val String.Companion.EMPTY: String
    get() = ""

val Int.Companion.NULL: Int
    get() = -1

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isShow(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun loadImage(view: View, url: String, place: ImageView, error: View, loading: View) {
    loading.show()
    error.gone()
    Glide.with(view)
        .load(url)
        .listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    loading.gone()
                    error.show()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    error.gone()
                    loading.gone()
                    return false
                }
            }
        )
        .into(place)
}

