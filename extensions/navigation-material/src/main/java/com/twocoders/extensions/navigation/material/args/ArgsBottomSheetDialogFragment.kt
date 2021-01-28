package com.twocoders.extensions.navigation.material.args

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.twocoders.extensions.navigation.toArgsBundle

open class ArgsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun setArguments(args: Bundle?) = super.setArguments(args.toArgsBundle())
}