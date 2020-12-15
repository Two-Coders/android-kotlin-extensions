package com.twocoders.extensions.common

import java.io.File

fun File.safeDelete(): Boolean = exists() && delete()
