package com.whoisacat.showcase.domain.entity

import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.*

data class DatePeriod(val startDate: LocalDate, val endDate: LocalDate? = null) {

    override fun toString(): String {
        return "${stringOfDate(startDate)}  — ${stringOfDate(endDate) ?: "настоящее время"} (${countDuration()})"
    }

    private fun stringOfDate(date: LocalDate?): String? {
        if (date == null) return null
        return Month.of(date.month.value)
            .getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru"))
            .plus(" ")
            .plus(date.year)
    }

    private fun countDuration(): String {
        val years = (endDate ?: LocalDate.now()).year.minus(startDate.year)
        if (years > 4) return "$years ${getFormOfTimePeriod(years, YEAR)}"
        val mounths = (endDate ?: LocalDate.now()).month.value.minus(startDate.month.value)
        if (mounths < 12) return "$mounths ${getFormOfTimePeriod(mounths, MOUNTH)}"
        return "$years ${getFormOfTimePeriod(years, YEAR)} $mounths ${getFormOfTimePeriod(mounths, MOUNTH)}"
    }

    private fun getFormOfTimePeriod(years: Int, key: String): String {
        val lastNumber: Int = years % 10
        val exclusion = years % 100 in 11..14

        return if (exclusion) {
            FORMS[MANY + key]!!
        } else {
            when (lastNumber) {
                1 -> FORMS[ONE + key]!!
                2, 3, 4 -> FORMS[FEW + key]!!
                else -> FORMS[MANY + key]!!
            }
        }
    }

    companion object {
        const val YEAR: String = "year"
        const val MOUNTH: String = "mounth"
        const val MANY = "many"
        const val FEW = "few"
        const val ONE = "one"
        val FORMS: Map<String, String> = mapOf(
            Pair("$MANY$YEAR", "лет"),
            Pair("$FEW$YEAR", "года"),
            Pair("$ONE$YEAR", "год"),
            Pair("$MANY$MOUNTH", "месяцов"),
            Pair("$FEW$MOUNTH", "месяца"),
            Pair("$ONE$MOUNTH", "месяц"),

            )
    }
}