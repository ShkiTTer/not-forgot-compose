package ru.shkitter.notforgot.presentation.common.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateFormattingUtils {
    private val fullDigitDateFormatter =
        DateTimeFormatter.ofPattern("dd.MM.yyyy")

    /**
     * 03.11.2020
     */
    fun getFullDigitDate(date: Instant) = fullDigitDateFormatter.format(date.atZone(ZoneId.systemDefault()))
}