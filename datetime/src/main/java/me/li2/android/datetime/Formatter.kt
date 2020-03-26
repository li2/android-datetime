package me.li2.android.datetime

import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import timber.log.Timber.e
import java.util.*

/*
RFC stands for Request For Comment. RFC is a formal document from the Internet Engineering Task Force (IETF)
 that is the result of committee drafting and subsequent review by interested parties.

 - “Z”: stands for Zero timezone (UTC+0). Or equal to +00:00 in the RFC 3339.
 - RFC 3339 is following the ISO 8601 DateTime format. The only difference is RFC allows us to replace “T” with “space”.

 https://medium.com/easyread/understanding-about-rfc-3339-for-datetime-formatting-in-software-engineering-940aa5d5f68a
 */
private const val RFC_DATE_TIME_PATTERN = "EEE, d MMM h:mma"
private const val ISO_LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

fun LocalDateTime.toStringWithPattern(pattern: String): String {
    return try {
        this.format(DateTimeFormatter.ofPattern(pattern))
    } catch (exception: Exception) {
        e(exception, "failed to format datetime: $this with pattern: $pattern")
        this.toString()
    }
}

fun LocalDateTime.toRfcDateTimeString() = this
        .toStringWithPattern(RFC_DATE_TIME_PATTERN)
        .replace("AM", "am")
        .replace("PM", "pm")

fun LocalDateTime.toIsoLocalDateTimeString() = this
        .toStringWithPattern(ISO_LOCAL_DATE_TIME_PATTERN)

fun Month.fullDisplayName(): String {
    return getDisplayName(TextStyle.FULL, Locale.getDefault())
}

fun Month.shortDisplayName(): String {
    return getDisplayName(TextStyle.SHORT, Locale.getDefault())
}
