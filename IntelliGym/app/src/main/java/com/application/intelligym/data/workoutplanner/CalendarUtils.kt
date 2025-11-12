package com.application.intelligym.data.workoutplanner

import java.time.LocalDate
import java.time.YearMonth

object CalendarUtils {

    /**
     * Generates a list of LocalDate objects representing the calendar days,
     * including previous month's last days and next month's starting days
     */
    fun daysInMonthArray(selectedDate: LocalDate): List<LocalDate> {
        val days = mutableListOf<LocalDate>()

        val yearMonth = YearMonth.from(selectedDate)
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val lastOfMonth = selectedDate.withDayOfMonth(yearMonth.lengthOfMonth())

        // Day of week (0 = Sunday, 1 = Monday, ..., 6 = Saturday)
        val firstDayOfWeek = firstOfMonth.dayOfWeek.value % 7

        // Previous month
        val previousMonth = selectedDate.minusMonths(1)
        val previousYearMonth = YearMonth.from(previousMonth)
        val previousMonthLastDay = previousYearMonth.lengthOfMonth()

        for (i in firstDayOfWeek downTo 1) {
            days.add(
                LocalDate.of(
                    previousMonth.year,
                    previousMonth.month,
                    previousMonthLastDay - i + 1
                )
            )
        }

        // Current month
        for (day in 1..yearMonth.lengthOfMonth()) {
            days.add(LocalDate.of(selectedDate.year, selectedDate.month, day))
        }

        // Fill remaining cells with next month days
        val nextMonth = selectedDate.plusMonths(1)
        val remainingCells = 35 - days.size
        for (day in 1..remainingCells) {
            days.add(LocalDate.of(nextMonth.year, nextMonth.month, day))
        }

        return days
    }
}