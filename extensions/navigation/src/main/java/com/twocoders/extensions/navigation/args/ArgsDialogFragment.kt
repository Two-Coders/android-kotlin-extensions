package com.twocoders.extensions.navigation.args

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.twocoders.extensions.navigation.toArgsBundle

open class ArgsDialogFragment : DialogFragment() {

    override fun setArguments(args: Bundle?) = super.setArguments(args.toArgsBundle())
}