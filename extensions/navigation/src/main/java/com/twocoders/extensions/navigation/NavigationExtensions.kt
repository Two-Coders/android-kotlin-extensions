package com.twocoders.extensions.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.navHostFragment(@IdRes navHostId: Int): NavHostFragment =
    supportFragmentManager.findFragmentById(navHostId) as? NavHostFragment
        ?: throw IllegalArgumentException("Provided $navHostId is not valid NavHostFragment id :/")

fun FragmentActivity.navController(@IdRes navHostId: Int): NavController =
    navHostFragment(navHostId).navController
