package org.calendar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       //test method here
    }

    public List<String[]> seeTimeBlocks (String[] calendar1, String[] dailyBounds1, String[] calendar2, String[] dailyBounds2, int meetingDuration) {
        // Extend and parse calendars
        List<int[]> extendedCalendar1 = extendCalendarWithBounds(calendar1, dailyBounds1);
        List<int[]> extendedCalendar2 = extendCalendarWithBounds(calendar2, dailyBounds2);

        // Merge calendars
        List<int[]> mergedCalendar = mergeCalendars(extendedCalendar1, extendedCalendar2);

        // Calculate the endOfWorkday in minutes from midnight
        int endOfWorkday1 = parseTimeToMinutes(dailyBounds1[1]);
        int endOfWorkday2 = parseTimeToMinutes(dailyBounds2[1]);
        int endOfWorkday = Math.min(endOfWorkday1, endOfWorkday2);

        // Find available time blocks
        findAvailableSlots(mergedCalendar, meetingDuration, endOfWorkday);
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

    /**
     * Merges and consolidates overlapping time blocks from two calendars into a single list.
     * Both input lists are assumed to include daily bounds and are formatted as start and end times
     * in minutes from midnight. The merged output list contains no overlapping time blocks.
     *
     * @param calendar1 First calendar's list of time blocks.
     * @param calendar2 Second calendar's list of time blocks.
     * @return A list of non-overlapping merged time blocks from both calendars.
     */
    private List<int[]> mergeCalendars(List<int[]> calendar1, List<int[]> calendar2) {
        List<int[]> merged = new ArrayList<>(calendar1);
        merged.addAll(calendar2);
        merged.sort(Comparator.comparingInt(a -> a[0])); // Sort by start time

        // Merge overlapping intervals
        List<int[]> mergedAndCleaned = new ArrayList<>();
        int[] currentMeeting = merged.get(0);
        mergedAndCleaned.add(currentMeeting);
        for (int[] meeting : merged) {
            if (meeting[0] <= currentMeeting[1]) { // Check for overlap
                currentMeeting[1] = Math.max(meeting[1], currentMeeting[1]);
            } else {
                currentMeeting = meeting;
                mergedAndCleaned.add(currentMeeting);
            }
        }
        return mergedAndCleaned;
    }

    /**
     * Identifies available slots between two merged calendars for scheduling a meeting.
     *
     * @param mergedCalendar Sorted list of occupied time blocks in minutes from midnight.
     * @param meetingDuration Duration of the meeting in minutes.
     * @param endOfWorkday Latest time a meeting can end, in minutes from midnight.
     * @return List of available time slots in "HH:MM" format.
     */
    private List<String[]> findAvailableSlots(List<int[]> mergedCalendar, int meetingDuration, int endOfWorkday) {
        List<String[]> availableSlots = new ArrayList<>();
        int endOfLastMeeting = mergedCalendar.get(0)[1]; // Assume starting after the first meeting

        for (int i = 1; i < mergedCalendar.size(); i++) {
            int startOfNextMeeting = mergedCalendar.get(i)[0];
            // Check if the gap between meetings is enough for the meetingDuration
            if (startOfNextMeeting - endOfLastMeeting >= meetingDuration) {
                // Add this time slot to the list of available slots
                availableSlots.add(new String[]{
                        minutesToTime(endOfLastMeeting),
                        minutesToTime(startOfNextMeeting)
                });
            }
            // Update the end of the last meeting to the end of the current meeting
            endOfLastMeeting = Math.max(endOfLastMeeting, mergedCalendar.get(i)[1]);
        }

        // Final check for the slot between the last meeting and the end of the workday
        if (endOfWorkday - endOfLastMeeting >= meetingDuration) {
            availableSlots.add(new String[] {
                    minutesToTime(endOfLastMeeting),
                    minutesToTime(endOfWorkday)
            });
        }

        return availableSlots;
    }

    /**
     * Converts minutes from midnight to a time string in "HH:MM" format.
     *
     * @param minutes The number of minutes from midnight.
     * @return A string representing the time in "HH:MM" format.
     */
    private String minutesToTime(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }

}