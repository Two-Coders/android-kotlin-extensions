package com.twocoders.extensions.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.twocoders.extensions.common.logd

@BindingAdapter(value = ["spanCount"])
fun RecyclerView.setSpanCount(spanCount: Int) {
    layoutManager.apply {
        when (this) {
            is StaggeredGridLayoutManager -> this.spanCount = spanCount
            is GridLayoutManager -> this.spanCount = spanCount
            else -> logd("SpanCount cannot be assigned to the unsupported RecyclerView LayoutManager: $this")
        }
    }
}
