package me.li2.android.datetime

import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId

fun LocalDateTime.withTime(time: LocalTime): LocalDateTime = this
        .withHour(time.hour).withMinute(time.minute).withSecond(time.second).withNano(time.nano)

fun LocalDateTime?.orNow(): LocalDateTime = this ?: LocalDateTime.now()

fun LocalDateTime.firstDayOfThisMonth(): LocalDateTime = this
        .withDayOfMonth(1)
        .withTime(LocalTime.of(0, 0, 0, 0))

fun LocalDateTime.millis(): Long = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

fun LocalDateTime.isSameDay(other: LocalDateTime) =
        this.year == other.year && this.dayOfYear == other.dayOfYear

fun LocalDateTime.isToday() = this.isSameDay(LocalDateTime.now())

fun LocalDateTime.isTomorrow() = this.isSameDay(LocalDateTime.now().plusDays(1))

fun LocalDateTime.isYesterday() = this.isSameDay(LocalDateTime.now().plusDays(-1))
