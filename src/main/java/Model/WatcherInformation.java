package Model;

import java.time.Instant;
import java.time.LocalTime;
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
    public void changedState(String deptId, long courseId) {
        if (this.deptId.equals(deptId) && this.courseId == courseId) {
            events.add(LocalTime.now() + " Added section ");
        }
    }

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

