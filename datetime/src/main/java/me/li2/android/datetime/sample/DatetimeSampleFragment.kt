package me.li2.android.datetime.sample

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jakewharton.threetenabp.AndroidThreeTen
import me.li2.android.datetime.Calculator.daysToChristmasEve
import me.li2.android.datetime.Calculator.daysToNewYear
import me.li2.android.datetime.Calculator.durationInDaysAndHours
import me.li2.android.datetime.R
import me.li2.android.datetime.fullDisplayName
import me.li2.android.datetime.shortDisplayName
import me.li2.android.datetime.toStringWithPattern
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month.*
import java.util.*

class DatetimeSampleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.datetime_sample_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.resultTextView).text = result()
    }

    // getBestDateTimePattern: GB: EEEE d MMMM >>>> "EEEE d MMMM" on Android 9, "EEEE, d MMMM" on Android 10)
    // getBestDateTimePattern: NZ: EEEE d MMMM >>>> "EEEE, d MMMM"
    // getBestDateTimePattern: US: EEEE d MMMM >>>> "EEEE, MMMM d"
    private val patterns = listOf(
        "yyyy-MM-dd'T'HH:mm:ss", // 2020-04-21T15:16:17 iso local data time
        "dd/MM/yyyy HH:mm:ss",   // 21/04/2020 15:16:17
        "dd/MM/yyyy",            // 21/04/2020
        "d MMM yyyy",            // 21 Apr 2020
        "yyyy-MM-dd",            // 2020-04-21
        "MMM dd'`'yy 'at' H:mm", // Apr 21'20 at 15:16
        "EEE, d MMM, h:mm a",    // Tue, 21 Apr, 3:16 pm
        "EEE, d MMM",            // Tue, 21 Apr
        "EEEE d MMMM",           // Tuesday 21 April
        "h:mm a",                // 3:16 pm
        "hh:mm a"                // 03:16 pm
    )

    private val months =
        listOf(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER)

    private fun formatDatetime(
        dateTime: LocalDateTime,
        pattern: String
    ): String {
        val default = dateTime.toStringWithPattern(pattern, Locale.getDefault(), false)
        val us = dateTime.toStringWithPattern(pattern, Locale.US)
        val uk = dateTime.toStringWithPattern(pattern, Locale.UK)
        val nz = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val localeNZ = Locale.Builder().setRegion("NZ").setLanguage("en").setLanguageTag("en-NZ").build()
            dateTime.toStringWithPattern(pattern, localeNZ)
        } else {
            ""
        }
        return "Default - $default\n" +
                "US best - $us\n" +
                "UK best - $uk\n" +
                "NZ best - $nz"
    }

    private fun monthsFullAndShortDisplayName(): String {
        return months.joinToString("\n") { month ->
            "${month.value} - ${month.fullDisplayName()} - ${month.shortDisplayName()}"
        }
    }

    private fun result(): String {
        //Initialize the timezone information
        AndroidThreeTen.init(requireActivity().application)

        val builder = StringBuilder()

        fun print(description: String, executor: () -> Any) {
            builder.appendln(description)
            builder.appendln("${executor()}")
            builder.appendln()
        }

        print("Days to new year") {
            daysToNewYear()
        }

        print("Days to Christmas eve") {
            daysToChristmasEve()
        }

        print("Month full and short display name") {
            monthsFullAndShortDisplayName()
        }

        val demoDateTime = LocalDateTime.of(2020, APRIL, 21, 15, 16, 17)
        patterns.forEach { pattern ->
            print("$demoDateTime in $pattern") {
                formatDatetime(demoDateTime, pattern)
            }
        }

        print("Duration between dates") {
            val now = LocalDateTime.now()
            durationInDaysAndHours(requireContext(), now, now.plusDays(5).plusHours(21))
        }

        return builder.toString()
    }
}
