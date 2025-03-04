package com.whoisacat.showcase.infrastructure.dto

import java.time.LocalDate

data class DatePeriodDto(val startDate: LocalDate, val endDate: LocalDate? = null) {
}