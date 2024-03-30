package org.calendar;

public class Main {
    public static void main(String[] args) {

        Calendar calendar = new Calendar();

        // Test case 1
        String[] calendar1 = {"9:00,10:30", "12:00,13:00", "16:00,18:00"};
        String[] dailyBounds1 = {"9:00", "20:00"};
        String[] calendar2 = {"10:00,11:30", "12:30,14:30", "14:30,15:00", "16:00,17:00"};
        String[] dailyBounds2 = {"10:00", "18:30"};
        int meetingDuration = 30;

        calendar.printTimeBlocks(calendar.getTimeBlocks(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration));
    }

}