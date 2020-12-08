package com.twocoders.util.commonextensions

import java.io.File

fun File.safeDelete(): Boolean = exists() && delete()
