package Model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Course> courseList = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public String getDepartmentName() {
        return departmentName;
    }
}
