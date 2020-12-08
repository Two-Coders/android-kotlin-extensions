package com.twocoders.util.commonextensions

import android.content.Context
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

private const val PATTERN_LOG_TO_FILE = "yyyy-MM-dd HH:mm:ss.SSS"

fun Any.logd(message: String) {
    Log.d(this::class.java.simpleName, message)
}

fun Any.logd(message: String, tr: Throwable) {
    Log.d(this::class.java.simpleName, message, tr)
}

fun Any.logToFile(context: Context, message: String) {
    val logFile = File(context.externalPrivateDir(), "log.txt")
    if (!logFile.exists()) {
        try {
            logFile.createNewFile()
        } catch (e: IOException) {
            logd("An error occurred while creating the log file :/")
        }
    }
    try {
        BufferedWriter(FileWriter(logFile, true)).apply {
            append("${Date(System.currentTimeMillis()).formatDateTime(PATTERN_LOG_TO_FILE)}: $message")
            newLine()
            close()
        }
    } catch (e: IOException) {
        logd("An error occurred while appending new line to the log file :/")
    }
}

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }