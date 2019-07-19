package com.jamun.vinsol.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jamun.vinsol.variables.ApiKeys.URL_DOMAIN
import com.jamun.vinsol.variables.ApiKeys.URL_IMAGE_DOWNLOAD

object DownloadImage {
    @JvmStatic
    fun downloadImages(
        context: Context,
        url: String,
        imageView: ImageView,
        placeHolder: Int
    ) {
         Glide.with(context)
            .load(URL_IMAGE_DOWNLOAD + url.replace("\\", ""))
            .placeholder(placeHolder)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(imageView)
    }
}
