package com.mezmeraiz.blank.common

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class MarginDecoration (private val verticalSpace: Int,
                        private val sideSpace: Int = verticalSpace) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = verticalSpace
        }
        outRect.bottom = verticalSpace
        outRect.right = sideSpace
        outRect.left = sideSpace
    }

}