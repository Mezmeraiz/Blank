package com.mezmeraiz.blank

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {

    @BindingAdapter("src")
    @JvmStatic
    fun setSrc(imageView: ImageView, src: String?){
        if (src != null && src.isNotEmpty()){
            Picasso.get().load(src).fit().centerInside().into(imageView)
        }
    }

}