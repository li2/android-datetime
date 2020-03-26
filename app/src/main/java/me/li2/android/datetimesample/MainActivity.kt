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

        LocalDateTime.now().toIsoLocalDateTimeString().toIsoLocalDateTime()?.let { now ->
            listOf(
                    "dd/MM/yyyy HH:mm:ss",   // 08/10/2019 14:36:42
                    "dd/MM/yyyy",            // 08/10/2019
                    "d MMM yyyy",            // 8 Oct 2019
                    "yyyy-MM-dd",            // 2019-10-08
                    "MMM dd'`'yy 'at' H:mm", // Oct 08'10 at 14:36
                    "EEE, d MMM, h:mm a",    // Thu, 21 Aug, 5:21 am
                    "EEE, d MMM",            // Thu, 21 Aug
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