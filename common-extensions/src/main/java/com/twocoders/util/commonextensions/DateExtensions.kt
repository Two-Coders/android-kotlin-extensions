package com.twocoders.util.commonextensions

import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

const val EPOCH_TIME = 0L

val TIME_ZONE_UTC: TimeZone = TimeZone.getTimeZone("UTC")

private const val ONE_DAY = 24L * 60L * 60L * 1000L
private const val ONE_WEEK = 7L * ONE_DAY
private const val ONE_MONTH = 31L * ONE_DAY
private const val ONE_YEAR = 365L * ONE_DAY

fun Date.now() = Date()

fun Date.epochTime() = Date(EPOCH_TIME)

fun Date.dayAgo() = Date(this.time - ONE_DAY)

fun Date.weekAgo() = Date(this.time - ONE_WEEK)

fun Date.monthAgo() = Date(this.time - ONE_MONTH)

fun Date.yearAgo() = Date(this.time - ONE_YEAR)

fun Date.formatDateTime(pattern: String): String = SimpleDateFormat(pattern, Locale.getDefault())
    .format(this)

fun Date.formatUtcDate(pattern: String): String = SimpleDateFormat(pattern, Locale.getDefault())
    .apply { timeZone = TIME_ZONE_UTC }
    .format(this)

fun Date.isBetweenDates(startDateInclusive: Date, endDateExclusive: Date) =
    this.after(startDateInclusive) && this.before(endDateExclusive)

fun Date.withDate(year: Int = 1970, month: Int = 1, day: Int = 1, hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Date =
    Calendar.getInstance().apply { set(year, month, day, hours, minutes, seconds) }.time

fun Date.formatTime(context: Context): String = DateFormat.getTimeFormat(context).format(this)

