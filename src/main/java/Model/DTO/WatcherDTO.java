package Model.DTO;

import Model.Course;
import Model.Department;

import java.util.List;

public class WatcherDTO {
    private int id;
    private Department department;
    private Course course;
    private List<String> events;

    public WatcherDTO(int id, Department department, Course course, List<String> events) {
        this.id = id;
        this.department = department;
        this.course = course;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public Course getCourse() {
        return course;
    }

    public List<String> getEvents() {
        return events;
    }
}
