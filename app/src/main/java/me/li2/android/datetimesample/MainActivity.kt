package me.li2.android.datetimesample

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import me.li2.android.datetime.Calculator.daysToChristmasEve
import me.li2.android.datetime.Calculator.daysToNewYear
import me.li2.android.datetime.Calculator.durationInDaysAndHours
import me.li2.android.datetime.fullDisplayName
import me.li2.android.datetime.shortDisplayName
import me.li2.android.datetime.toIsoLocalDateTimeString
import me.li2.android.datetime.toStringWithPattern
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.resultTextView).text = demo()
    }

    private fun demo(): String {
        AndroidThreeTen.init(this)
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
            listOf(JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER)
                    .joinToString("\n") { month ->
                        "${month.value} - ${month.fullDisplayName()} - ${month.shortDisplayName()}"
                    }
        }

        // 2020-04-21T05:21:13
        val demoDateTime = LocalDateTime.of(2020, APRIL, 21, 15, 16, 17, 140)
        val demoDatetimeStr = demoDateTime.toIsoLocalDateTimeString()
        builder.appendln("Demo datetime: $demoDatetimeStr")

        // getBestDateTimePattern: GB: EEEE d MMMM >>>> "EEEE d MMMM" on Android 9, "EEEE, d MMMM" on Android 10)
        // getBestDateTimePattern: NZ: EEEE d MMMM >>>> "EEEE, d MMMM"
        // getBestDateTimePattern: US: EEEE d MMMM >>>> "EEEE, MMMM d"

        listOf(
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
        ).forEach { pattern ->
            print(pattern) {
                val us = demoDateTime.toStringWithPattern(pattern, Locale.US)
                val uk = demoDateTime.toStringWithPattern(pattern, Locale.UK)
                val nz = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val localeNZ = Locale.Builder().setRegion("NZ").setLanguage("en").setLanguageTag("en-NZ").build()
                    demoDateTime.toStringWithPattern(pattern, localeNZ)
                } else {
                    ""
                }
                "US - $us\nUK - $uk\nNZ - $nz"
            }
        }

        print("Duration") {
            durationInDaysAndHours(this,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(5).plusHours(21))
        }

        return builder.toString()
    }
}