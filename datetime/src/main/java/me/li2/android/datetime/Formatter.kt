package me.li2.android.datetime

import android.text.format.DateFormat
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

fun LocalDateTime.toStringWithPattern(
        pattern: String,
        locale: Locale = Locale.getDefault(),
        allowBestLocalized: Boolean = true): String {
    return try {
        val bestPattern = if (allowBestLocalized) DateFormat.getBestDateTimePattern(locale, pattern) else pattern
        this.format(DateTimeFormatter.ofPattern(bestPattern, locale))
    } catch (exception: Exception) {
        e(exception, "failed to format datetime: $this with pattern: $pattern")
        this.toString()
    }
}

fun LocalDateTime.toIsoLocalDateTimeString(): String =
        this.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

fun Month.fullDisplayName(): String {
    return getDisplayName(TextStyle.FULL, Locale.getDefault())
}

fun Month.shortDisplayName(): String {
    return getDisplayName(TextStyle.SHORT, Locale.getDefault())
}
