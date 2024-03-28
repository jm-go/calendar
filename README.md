# Meeting Scheduler

## Overview
This project provides a Java solution for scheduling a meeting between two individuals within their available times. Given both individuals' daily schedules and bounds, it calculates all possible time blocks where a meeting of a certain duration can be scheduled.

## Features
- **Calculate Meeting Times:** Given two calendars, daily bounds, and meeting duration, it identifies all possible meeting slots.
- **Military Time Format:** Inputs and outputs times in military format (e.g., `8:30`, `14:00`).

## Input Format
- **Calendars:** Two lists of `[startTime, endTime]` pairs representing scheduled meetings (e.g., `[['9:00', '10:30'], ['12:00', '13:00']]`).
- **Daily Bounds:** Two `[earliestTime, latestTime]` pairs indicating the earliest and latest times available for meetings each day (e.g., `['9:00', '20:00']`).
- **Meeting Duration:** The required duration of the meeting in minutes (e.g., `30`).

## Sample Input
```java
String[] calendar1 = {"9:00,10:30", "12:00,13:00", "16:00,18:00"};
String[] dailyBounds1 = {"9:00", "20:00"};
String[] calendar2 = {"10:00,11:30", "12:30,14:30", "14:30,15:00", "16:00,17:00"};
String[] dailyBounds2 = {"10:00", "18:30"};
int meetingDuration = 30; 
```

## Sample Output
```java
[["11:30", "12:00"], ["15:00", "16:00"], ["18:00", "18:30"]]
```

## Development Goals
- **Clean Code:** Begin with a straightforward solution and refine it to enhance clarity and efficiency.
- **Testing:** Implement multiple test cases to ensure the solution works as intended across various scenarios.

## Contact
For any additional questions or comments, feel free to reach out to me directly through the contact information provided on my GitHub page.