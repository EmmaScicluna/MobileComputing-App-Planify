package com.example.planifyfinal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Utility class for calendar related operations
public class CalendarUtils {
    public static LocalDate selectedDate; // The date that is currently selected

    public static String formattedDate(LocalDate date) { // Formatting the date to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return date.format(formatter);
    }

    public static String formattedTime(LocalTime time) { // Formatting the time to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return time.format(formatter);
    }

    public static String formattedShortTime(LocalTime time) { // Formatting the time to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    public static String monthYearFromDate(LocalDate date) { // Formatting the month and year to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public static String monthDayFromDate(LocalDate date) { // Formatting the month and day to a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d");
        return date.format(formatter);
    }

    // Method to get an array of days in a month
    public static ArrayList<LocalDate> daysInMonthArray() {
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>(); // ArrayList to store the days in a month
        YearMonth yearMonth = YearMonth.from(selectedDate);

        int daysInMonth = yearMonth.lengthOfMonth(); // Getting the number of days in the month

        LocalDate prevMonth = selectedDate.minusMonths(1); // Getting the previous month
        LocalDate nextMonth = selectedDate.plusMonths(1); // Getting the next month

        YearMonth prevYearMonth = YearMonth.from(prevMonth); // Getting the year and month of the previous month
        int prevDaysInMonth = prevYearMonth.lengthOfMonth(); // Getting the number of days in the previous month

        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) { // Looping through the days of the month to fill the array
            if(i <= dayOfWeek) {
                daysInMonthArray.add(LocalDate.of(prevMonth.getYear(),prevMonth.getMonth(),prevDaysInMonth + i - dayOfWeek));
            } else if (i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add(LocalDate.of(nextMonth.getYear(),nextMonth.getMonth(),i - dayOfWeek - daysInMonth));
            } else {
                daysInMonthArray.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonth(),i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    // Method to get an array of days in a week
    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current = sundayForDate(selectedDate);
        LocalDate endDate = current.plusWeeks(1);

        // Loop to fill the array with the days of the week
        while (current.isBefore(endDate)) {
            days.add(current);
            current = current.plusDays(1);
        }

        return days;
    }

    // Method to get the Sunday for a given date
    private static LocalDate sundayForDate(LocalDate current) {
        LocalDate oneWeekAgo = current.minusWeeks(1);

        while (current.isAfter(oneWeekAgo)) { // Loop to find the Sunday
            if (current.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return current;
            }
            current = current.minusDays(1);
        }
        return null;
    }



}
