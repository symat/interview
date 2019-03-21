package com.symat.interview.due_date;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DueDateTimeCalculatorTest {

    private static final LocalDateTime MONDAY_NOON = LocalDateTime.parse("2019-03-18T12:00:00");
    private static final LocalDateTime MONDAY_2_PM = LocalDateTime.parse("2019-03-18T14:00:00");
    private static final LocalDateTime MONDAY_5_PM = LocalDateTime.parse("2019-03-18T17:00:00");
    private static final LocalDateTime MONDAY_8_PM = LocalDateTime.parse("2019-03-18T20:00:00");
    private static final LocalDateTime TUESDAY_11_AM = LocalDateTime.parse("2019-03-19T11:00:00");
    private static final LocalDateTime SUNDAY_NOON = LocalDateTime.parse("2019-03-24T12:00:00");
    private static final LocalDateTime NEXT_TUESDAY_NOON = LocalDateTime.parse("2019-03-26T12:00:00");

    private static final Duration NEGATIVE = Duration.ofHours(-1);
    private static final Duration IMMEDIATELY = Duration.ZERO;
    private static final Duration TWO_HOURS = Duration.ofHours(2);
    private static final Duration FIVE_HOURS = Duration.ofHours(5);
    private static final Duration SEVEN_HOURS = Duration.ofHours(7);
    private static final Duration SIX_WORK_DAYS = Duration.ofHours(6 * 8);

    @Test
    public void shouldCalculateDueDate() {
        assertThat(DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, TWO_HOURS), equalTo(MONDAY_2_PM));
    }

    @Test
    public void shouldCalculateImmediateDueDate() {
        assertThat(DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, IMMEDIATELY), equalTo(MONDAY_NOON));
    }

    @Test
    public void shouldCalculateDueDateForEndOfWorkingDay() {
        assertThat(DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, FIVE_HOURS), equalTo(MONDAY_5_PM));
    }

    @Test
    public void shouldCalculateDueDateForNextDay() {
        assertThat(DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, SEVEN_HOURS), equalTo(TUESDAY_11_AM));
    }

    @Test
    public void shouldCalculateDueDateForNextWeek() {
        assertThat(DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, SIX_WORK_DAYS), equalTo(NEXT_TUESDAY_NOON));
    }


    // testing illegal arguments

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfSubmitDateIsNull() {
        DueDateTimeCalculator.calculateDueDateTime(null, TWO_HOURS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfTurnaroundTimeIsNegative() {
        DueDateTimeCalculator.calculateDueDateTime(MONDAY_NOON, NEGATIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfSubmitDateIsOutsideWorkHours() {
        DueDateTimeCalculator.calculateDueDateTime(MONDAY_8_PM, TWO_HOURS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfSubmitDateIsOutsideWorkDays() {
        DueDateTimeCalculator.calculateDueDateTime(SUNDAY_NOON, TWO_HOURS);
    }

    @Test(expected = DateTimeException.class)
    public void shouldThrowIfDueDateIsAfterMaxTime() {
        DueDateTimeCalculator.calculateDueDateTime(LocalDateTime.of(LocalDate.MAX, LocalTime.NOON), SIX_WORK_DAYS);
    }


}