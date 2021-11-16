package com.example.githubaqwastask.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.githubaqwastask.MyApp.Companion.context
import com.example.githubaqwastask.R

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context!!).load(url).dontAnimate().into(view)
    } else {
        view.setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.ic_launcher_background
            )
        )
    }
}
