package com.whoisacat.showcase.back.domain.entity

import java.time.LocalDate
import java.time.Month
import java.time.Period
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
        val period = Period.between(startDate, endDate ?: LocalDate.now())
        val years = period.years
        val months = period.months + if (period.days > 20) 1 else 0

        return (if (years > 0) "$years ${getFormOfTimePeriod(years, YEAR)}" else "") +
                (if (years > 0 && months > 0) " " else "") +
                if (months > 0) "$months ${getFormOfTimePeriod(months, MONTH)}" else ""
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
        const val MONTH: String = "month"
        const val MANY = "many"
        const val FEW = "few"
        const val ONE = "one"
        val FORMS: Map<String, String> = mapOf(
            Pair("$MANY$YEAR", "лет"),
            Pair("$FEW$YEAR", "года"),
            Pair("$ONE$YEAR", "год"),
            Pair("$MANY$MONTH", "месяцов"),
            Pair("$FEW$MONTH", "месяца"),
            Pair("$ONE$MONTH", "месяц"),

            )
    }
}