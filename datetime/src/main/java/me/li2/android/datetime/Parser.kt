package me.li2.android.datetime

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import timber.log.Timber

// java 8 date time format
// https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

fun String?.toIsoLocalDateTime(): LocalDateTime? {
    if (this == null) return null
    return try {
        LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    } catch (exception: DateTimeParseException) {
        Timber.e(
            exception,
            "failed parse datetime string: $this with pattern ${DateTimeFormatter.ISO_LOCAL_DATE_TIME}"
        )
        null
    }
}
