package com.whoisacat.showcase.contract.back.dto

import java.time.LocalDate

data class DatePeriodDto(val startDate: LocalDate, val endDate: LocalDate? = null) {
}