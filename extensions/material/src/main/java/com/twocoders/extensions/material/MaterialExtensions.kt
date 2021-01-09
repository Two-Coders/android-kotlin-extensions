package com.twocoders.extensions.material

import android.content.DialogInterface
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.twocoders.dynamic.text.DynamicText
import com.twocoders.extensions.material.component.AlertDialogComponent

val BottomSheetDialogFragment.behavior: BottomSheetBehavior<FrameLayout>?
    get() = bottomSheetDialog?.behavior

val BottomSheetDialogFragment.bottomSheetDialog: BottomSheetDialog?
    get() = dialog as? BottomSheetDialog

fun Fragment.showMaterialAlertDialog(
    component: AlertDialogComponent,
    buttonClickListener: DialogInterface.OnClickListener? = null,
    dismissListener: DialogInterface.OnDismissListener? = null
) {
    context?.let {
        MaterialAlertDialogBuilder(it)
            .apply {
                if (component.title != DynamicText.EMPTY) setTitle(component.title.getText(it))
                if (component.message != DynamicText.EMPTY) setMessage(component.message.getText(it))
                if (component.positiveButtonText != DynamicText.EMPTY) setPositiveButton(
                    component.positiveButtonText.getText(it), buttonClickListener
                )
                if (component.negativeButtonText != DynamicText.EMPTY) setNegativeButton(
                    component.negativeButtonText.getText(it), buttonClickListener
                )
            }
            .setOnDismissListener(dismissListener)
            .show()
    }
}