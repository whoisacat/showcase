package com.whoisacat.showcase.back.domain.entity

import java.time.LocalDate
import mu.KotlinLogging
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DatePeriodTest {

    private val logger = KotlinLogging.logger {}

    @Test
    fun periodLessThan20daysIsRoundingDown() {

        val datePeriod = DatePeriod(LocalDate.of(2022, 11, 29), LocalDate.of(2024, 2, 14))

        logger.info { "datePeriod $datePeriod" }

        assertEquals("ноябрь 2022  — февраль 2024 (1 год 2 месяца)", datePeriod.toString())
    }

    @Test
    fun periodMoreThan20daysIsRoundingUp() {

        val datePeriod = DatePeriod(LocalDate.of(2022, 11, 29), LocalDate.of(2024, 2, 19))

        logger.info { "datePeriod $datePeriod" }

        assertEquals("ноябрь 2022  — февраль 2024 (1 год 3 месяца)", datePeriod.toString())
    }

    @Test
    fun periodOneYearHasNoSpaceAtEnd() {

        val datePeriod = DatePeriod(LocalDate.of(2022, 11, 29), LocalDate.of(2023, 11, 30))

        logger.info { "datePeriod $datePeriod" }

        assertEquals("ноябрь 2022  — ноябрь 2023 (1 год)", datePeriod.toString())
    }
}