package me.li2.android.datetimesample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import me.li2.android.datetime.*
import me.li2.android.datetime.Calculator.daysToChristmasEve
import me.li2.android.datetime.Calculator.daysToNewYear
import me.li2.android.datetime.Calculator.durationInDaysAndHours
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.resultTextView).text = demo()
    }

    private fun demo(): String {
        AndroidThreeTen.init(this)

        val builder = StringBuilder()
        builder.appendln("Days to new year: ${daysToNewYear()}")
        builder.appendln("Days to Christmas eve: ${daysToChristmasEve()}")
        builder.appendln("Month full display name: ${LocalDate.now().month.fullDisplayName()}")
        builder.appendln("Month short display name: ${LocalDate.now().month.shortDisplayName()}")

        // 2020-04-21T05:21:13
        val demoDateTime = LocalDateTime.of(2020, Month.APRIL, 21, 5, 21, 13, 140)
        val currentDatetimeStr = demoDateTime.toIsoLocalDateTimeString()
        builder.appendln("Current datetime: $currentDatetimeStr")

        currentDatetimeStr.toIsoLocalDateTime()?.let { now ->
            listOf(
                    "dd/MM/yyyy HH:mm:ss",   // 21/04/2020 05:21:13
                    "dd/MM/yyyy",            // 21/04/2020
                    "d MMM yyyy",            // 21 Apr 2020
                    "yyyy-MM-dd",            // 2020-04-21
                    "MMM dd'`'yy 'at' H:mm", // Apr 21'20 at 5:21
                    "EEE, d MMM, h:mm a",    // Thu, 21 Apr, 5:21 am
                    "EEE, d MMM",            // Thu, 21 Apr
                    "h:mm a",                // 5:21 am
                    "hh:mm a"                // 05:21 am
            ).forEach { pattern ->
                builder.appendln("$pattern <-> ${now.toStringWithPattern(pattern)}")
            }
        }

        val durationInDaysAndHours = durationInDaysAndHours(this,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(5).plusHours(21))
        builder.appendln("Duration: $durationInDaysAndHours")

        return builder.toString()
    }
}