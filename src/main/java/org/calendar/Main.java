package org.calendar;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       //test method here
    }

    public List<String[]> seeTimeBlocks (String[] calendar1, String[] dailyBounds1, String[] calendar2, String[] dailyBounds2, int meetingDuration) {
        //TODO: everything
        return null;
    }

    /* Convert time strings into minutes since midnight. */
    private int parseTimeToMinutes(String time) {
        String[] timeParts = time.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Extends the calendar with the daily bounds specified for a person's working day.
     * This method converts all time slots and bounds from String to minutes from midnight,
     * and it adds a block before the start and after the end of the working day as defined by dailyBounds.
     *
     * @param calendar An array of Strings where each String represents a meeting time block in the format "HH:MM,HH:MM".
     * @param dailyBounds An array of 2 Strings representing the start and end of the working day in the format "HH:MM".
     * @return A list of int arrays, where each int array represents a time block in minutes from midnight.
     *         The list includes the original meetings from the calendar and additional blocks before and after the working day.
     */
    private List<int[]> extendCalendarWithBounds(String[] calendar, String[] dailyBounds) {
        List<int[]> extendedCalendar = new ArrayList<>();
        extendedCalendar.add(new int[]{0, parseTimeToMinutes(dailyBounds[0])});
        for (String meeting : calendar) {
            String[] times = meeting.split(",");
            extendedCalendar.add(new int[]{parseTimeToMinutes(times[0].trim()), parseTimeToMinutes(times[1].trim())});
        }
        extendedCalendar.add(new int[]{parseTimeToMinutes(dailyBounds[1]), 24 * 60});
        return extendedCalendar;
    }


}