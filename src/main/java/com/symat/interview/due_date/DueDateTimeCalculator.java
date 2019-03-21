package com.symat.interview.due_date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Arrays.asList;

public class DueDateTimeCalculator {

    private static final Set<DayOfWeek> HOLIDAYS = new HashSet<>(asList(SATURDAY, SUNDAY));
    private static final LocalTime WORKING_HOURS_START = LocalTime.parse("09:00:00");
    private static final LocalTime WORKING_HOURS_END = LocalTime.parse("17:00:00");


    /**
     * The calculateDueDateTime function will return with the deadline until the issue is to be resolved, assuming that
     * people will work on the issue only during working hours. The function takes only the weekends as holidays and
     * expects every working day to last between 9 AM and 5 PM.
     *
     * @param submitDateTime {@code LocalDateTime} of the issue submission, expected to be during working hours
     * @param turnaroundTime {@code Duration}, the maximum time can be spent on the issue (non-negative, but can be zero)
     * @return {@code LocalDateTime} (not null), until the issue is to be resolved
     * @throws IllegalArgumentException    if either parameter is null, submitDateTime is not during working hours
     *                                     or turnaround time is negative
     * @throws java.time.DateTimeException if you end up so far in the future that standard Java {@code LocalDate}
     *                                     and {@code LocalDateTime} can not store the date anymore
     *                                     (c'mon, you should be more optimistic about fixing this issue... ;) )
     */
    public static LocalDateTime calculateDueDateTime(final LocalDateTime submitDateTime, final Duration turnaroundTime) {
        validateArgs(submitDateTime, turnaroundTime);

        // LocalDateTime and Duration are immutable classes, no need for cloning them, input objects will not be modified
        Duration remainingTime = turnaroundTime;
        LocalDateTime currentDateTime = submitDateTime;
        LocalDateTime dueDateTime = submitDateTime;

        while (!remainingTime.isZero() && !remainingTime.isNegative()) {
            final LocalDateTime currentWorkDayEnd = WORKING_HOURS_END.atDate(currentDateTime.toLocalDate());
            final LocalDateTime dueDateWithoutPause = currentDateTime.plus(remainingTime);
            dueDateTime = minimumDateTime(currentWorkDayEnd, dueDateWithoutPause);

            remainingTime = remainingTime.minus(Duration.between(currentDateTime, dueDateTime));
            currentDateTime = getNextWorkDayStart(currentDateTime);
        }

        return dueDateTime;
    }


    private static LocalDateTime minimumDateTime(final LocalDateTime t1, final LocalDateTime t2) {
        if (t1.isBefore(t2)) {
            return t1;
        }
        return t2;
    }


    private static LocalDateTime getNextWorkDayStart(final LocalDateTime currentTime) {
        LocalDate nextDay = currentTime.toLocalDate().plusDays(1);
        while (HOLIDAYS.contains(nextDay.getDayOfWeek())) {
            nextDay = nextDay.plusDays(1);
        }
        return WORKING_HOURS_START.atDate(nextDay);
    }


    private static void validateArgs(final LocalDateTime submitDateTime, final Duration turnaroundTime) {
        if (submitDateTime == null || turnaroundTime == null) {
            throw new IllegalArgumentException("illegal NULL value for submit date time or turnaround time");
        }

        if (turnaroundTime.isNegative()) {
            throw new IllegalArgumentException("turnaround time can not be negative");
        }

        if (submitDateTime.toLocalTime().isBefore(WORKING_HOURS_START) ||
            submitDateTime.toLocalTime().isAfter(WORKING_HOURS_END) ||
            HOLIDAYS.contains(submitDateTime.getDayOfWeek())) {
            throw new IllegalArgumentException("submit date time must be during working hours");
        }
    }
}
