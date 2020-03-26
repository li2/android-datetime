package me.li2.android.datetime

import android.content.Context
import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import org.threeten.bp.temporal.ChronoUnit

object Calculator {
    private val NOW
        get() = LocalDateTime.now()

    private val CHRISTMAS_EVE
        get() = LocalDateTime.of(LocalDate.now().year, Month.DECEMBER, 24, 0, 0)

    private val NEW_YEAR
        get() = NOW.plusYears(1).withDayOfYear(1)

    fun daysToNewYear(): Long = ChronoUnit.DAYS.between(NOW, NEW_YEAR)

    fun daysToChristmasEve(): Long = ChronoUnit.DAYS.between(NOW, CHRISTMAS_EVE)

    fun durationInDaysAndHours(from: LocalDateTime, to: LocalDateTime): Pair<Int, Int> {
        val duration = Duration.between(from, to)
        val days = duration.toDays()
        val hours = duration.toHours() % 24 + if (duration.toMinutes() % 60 > 0) 1 else 0
        return Pair(days.toInt(), hours.toInt())
    }

    /** Return duration in formatted x days and y hours*/
    fun durationInDaysAndHours(context: Context, from: LocalDateTime, to: LocalDateTime): String {
        val (days, hours) = durationInDaysAndHours(from, to)
        val daysWithUnit = context.resources.getQuantityString(R.plurals.datetimeLib_days, days, days)
        val hoursWithUnit = context.resources.getQuantityString(R.plurals.datetimeLib_hours, hours, hours)
        return when {
            days > 0 && hours > 0 -> "$daysWithUnit and $hoursWithUnit"
            days > 0 && hours == 0 -> daysWithUnit
            else -> hoursWithUnit
        }
    }
}
