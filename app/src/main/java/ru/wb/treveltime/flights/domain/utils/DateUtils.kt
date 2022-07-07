package ru.wb.treveltime.flights.domain.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

private const val MS_SECONDS_PER_DAY = 86_400_000

fun String.dateFormatList(): String {
    val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
    val newFormat = SimpleDateFormat("dd MMM yyyy г.", Locale.getDefault())
    return newFormat.format(oldFormat as Date)
}

fun differenceBetweenDates(startDay: String, finishDay: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val firstDate = format.parse(startDay)
    val secondDate = format.parse(finishDay)
    val difference = abs(firstDate.time - secondDate.time)
    val differenceDates = difference / MS_SECONDS_PER_DAY
    return differenceDates.toString()
}