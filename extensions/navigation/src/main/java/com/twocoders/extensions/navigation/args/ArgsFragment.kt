package com.twocoders.extensions.navigation.args

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.twocoders.extensions.navigation.toArgsBundle

open class ArgsFragment : Fragment() {

    override fun setArguments(args: Bundle?) = super.setArguments(args.toArgsBundle())
}