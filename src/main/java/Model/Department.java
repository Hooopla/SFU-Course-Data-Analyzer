package Model;

import java.util.*;

public class Department {
    private String name;
    private String deptId;
    private List<Course> courseList = new ArrayList<>();

    public Department(String departmentName) {
        this.name = departmentName;
        this.deptId = departmentName;
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
            sortCourseListByCatalogNumber();
        }
    }

    public void sortCourseListByCatalogNumber() {
        courseList.sort(Comparator.comparing(course -> course.getCatalogNumber()));
    }

    public Course getCourseById(long courseId) {
        for (Course course: courseList) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }

        throw new IllegalStateException("Could not find matching courseId, searching for: " + courseId);
    }

    public String getName() {
        return name;
    }

    public String getDeptId() {
        return deptId;
    }

    public Course getCourse(int index) {
        return courseList.get(index);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public List<Grapher> getGraphData() {
        List<Grapher> data = new ArrayList<>();
        Set<Integer> semesterIdsSet = new HashSet<>();  // Using a Set to ensure uniqueness

        for (Course course : courseList) {
            semesterIdsSet.add(course.getSemesterId());
        }
        for (Integer semesterId : semesterIdsSet) {
            int totalEnrollments = 0;
            for (Course course : courseList) {
                if (course.getSemesterId() == semesterId) {
                    totalEnrollments += course.getTotalEnrollmentUsingSemId(semesterId);
                }
            }
            Grapher newGraphDot = new Grapher(semesterId, totalEnrollments);
            data.add(newGraphDot);
        }
        graphDataDump(data);
        return data;
    }

    public void graphDataDump(List<Grapher> dataSet) {
        for(Grapher data : dataSet) {
            System.out.println(data);
        }
    }
}
