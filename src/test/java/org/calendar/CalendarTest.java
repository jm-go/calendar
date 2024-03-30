package org.calendar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

    @Test
    @DisplayName("Basic Scenario: Identifies correct available time blocks.")
    void basicScenario_IdentifiesCorrectAvailableTimeBlocks() {
        Calendar calendar = new Calendar();
        String[] calendar1 = {"9:00,10:30", "12:00,13:00", "16:00,18:00"};
        String[] dailyBounds1 = {"8:00", "20:00"};
        String[] calendar2 = {"10:00,11:30", "12:30,14:30", "14:30,15:00", "16:00,17:00"};
        String[] dailyBounds2 = {"10:00", "18:30"};
        int meetingDuration = 30;

        List<String[]> actual = calendar.getTimeBlocks(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);
        String[][] expected = {{"11:30", "12:00"}, {"15:00", "16:00"}, {"18:00", "18:30"}};

        assertArrayEquals(expected, actual.toArray(new String[0][0]));
    }

    @Test
    @DisplayName("No Available Blocks: Correctly identifies when no blocks are available.")
    void noAvailableBlocks_CorrectlyIdentifiesWhenNoBlocksAreAvailable() {
        Calendar calendar = new Calendar();
        String[] calendar1 = {"9:00,17:00"};
        String[] dailyBounds1 = {"8:00", "20:00"};
        String[] calendar2 = {"9:00,17:00"};
        String[] dailyBounds2 = {"9:00", "17:00"};
        int meetingDuration = 60;

        List<String[]> actual = calendar.getTimeBlocks(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Short Meeting Duration: Identifies time blocks for a short meeting.")
    void shortMeetingDuration_IdentifiesTimeBlocks() {
        Calendar calendar = new Calendar();
        String[] calendar1 = {"9:00,9:30", "12:00,12:30", "15:00,15:30"};
        String[] dailyBounds1 = {"8:00", "16:00"};
        String[] calendar2 = {"9:30,10:00", "12:30,13:00", "15:30,16:00"};
        String[] dailyBounds2 = {"8:30", "16:30"};
        int meetingDuration = 15;

        List<String[]> actual = calendar.getTimeBlocks(calendar1, dailyBounds1, calendar2, dailyBounds2, meetingDuration);
        String[][] expected = {{"08:30", "09:00"}, {"10:00", "12:00"}, {"13:00", "15:00"}};

        assertArrayEquals(expected, actual.toArray(new String[0][0]));
    }


}