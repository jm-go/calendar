package org.calendar;

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
}