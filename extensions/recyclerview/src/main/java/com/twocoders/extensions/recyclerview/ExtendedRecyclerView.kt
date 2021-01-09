package com.twocoders.extensions.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * A [ExtendedRecyclerView] is an extended version of the standard [RecyclerView] class.
 * Automatically removes actual adapter reference in the [onDetachedFromWindow] method.
 */
open class ExtendedRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        if (adapter != null) adapter = null
    }
}