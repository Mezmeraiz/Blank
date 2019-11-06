package com.mezmeraiz.blank

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(message: String? = "unknown error", length: Int = Snackbar.LENGTH_SHORT) {
    activity?.let {
        Snackbar.make(it.window.decorView.rootView, message.toString(),
            length).show()
    }
}
