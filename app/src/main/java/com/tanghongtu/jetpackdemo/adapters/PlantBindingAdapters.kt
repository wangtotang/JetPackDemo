package com.tanghongtu.jetpackdemo.adapters

import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tanghongtu.jetpackdemo.R

/**
 * Created by tanghongtu on 2020/5/27.
 */
@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("wateringText")
fun bindWateringText(view: TextView, wateringInterval: Int) {
   view.text = view.context.resources.getQuantityString(
       R.plurals.watering_needs_suffix,
       wateringInterval,
       wateringInterval
   )

}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    view.apply {
        if (TextUtils.isEmpty(description)) {
            text = ""
        } else {
            text = HtmlCompat.fromHtml(description!!, HtmlCompat.FROM_HTML_MODE_COMPACT)
            movementMethod = LinkMovementMethod.getInstance()
        }
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean?) {
    view.visibility = if (isGone == null || isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}