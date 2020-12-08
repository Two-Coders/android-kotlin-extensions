package com.twocoders.util.commonextensions

import java.util.*

val EMPTY_UUID: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000")
val INVALID_UUID: UUID = UUID.fromString("99999999-9999-9999-9999-999999999999")

fun UUID.isEmpty() = this == EMPTY_UUID
fun UUID.isNotEmpty() = !isEmpty()

fun UUID.isValid() = this != INVALID_UUID
fun UUID.isNotValid() = !isValid()