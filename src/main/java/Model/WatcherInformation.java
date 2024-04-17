package Model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WatcherInformation implements Observer {
    private int length = 0;
    private String deptId;
    private long courseId;
    private List<String> events = new ArrayList<>();

    public WatcherInformation(String deptId, long courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }

    // Log the new event
    @Override
    public void changedState(String deptId, long courseId, CourseData data) {
        if (this.deptId.equals(deptId) && this.courseId == courseId) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                    "EEE MMM dd HH:mm:ss z yyyy"
            );
            ZonedDateTime currentTime = ZonedDateTime.now();
            String formattedDateTime = currentTime.format(dateTimeFormatter);
            events.add(
                    formattedDateTime +
                    " Added section for " +
                    data.getComponent() +
                    " with enrollment (" +
                    data.getEnrollmentTotal() +
                    " / " + data.getEnrollmentCap() +
                   ") to offering " + CourseOfferings.calculateTerm(data) +
                   " " + CourseOfferings.calculateYear(data)
            );
        }
    }

    @Override
    public List<String> getEvents() {
        return events;
    }

    public int getLength() {
        return length;
    }

    public String getDeptId() {
        return deptId;
    }

    public long getCourseId() {
        return courseId;
    }
}

