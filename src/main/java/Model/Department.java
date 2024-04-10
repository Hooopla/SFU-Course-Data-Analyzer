package Model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentName;
    private List<Course> courseList = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    // Pre-condition: The departmentName and courseData.subjectName are the same.
    // Post-condition: Create CourseOffering for existing course, create new course and attach offering if new.
    public void addCourse(CourseData courseData) {
        boolean catalogNumberFound = false;
        // Check if the course already exists (Such as CMPT 250)
        // if the course already exists, then add a CourseOffering to it
        // TODO: Create a CourseOffering Object and add it to the existing course.courseOfferingList
        for (Course course: courseList) {
            if (course.getCatalogNumber().equals(courseData.getCatalogNumber())) {
                course.addCourseOffering(courseData);
                catalogNumberFound = true;
                break;
            }
        }

        // If not, add this new course to the courseList
        // TODO: courseList.add(course);
        if (!catalogNumberFound) {
            int id = courseList.size() + 1;
            Course newCourse = new Course(id, courseData);
            newCourse.addCourseOffering(courseData);
            courseList.add(newCourse);
        }
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Course getCourse(int index) {
        return courseList.get(index);
    }
}
