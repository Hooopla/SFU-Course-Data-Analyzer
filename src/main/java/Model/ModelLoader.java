package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Opens and loads the information from a CSV file into its respective objects.
 */
public class ModelLoader {
    private String path, line;
    private String[] values;
    public ModelLoader(String fileLocation, List<Department> departmentList) {
        path = fileLocation;
        line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine(); // Skips the very first row of the CSV, due to it being the headers

            while ((line = br.readLine()) != null) {
                values = line.split(",");
                CourseData data = createCourseData();

                addToDepartment(departmentList, data);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Invalid path to file: " + e);
        }
    }

    private CourseData createCourseData() {
        int semesterId = Integer.parseInt(values[0]);
        String subject = values[1];
        String catalogNumber = values[2];
        String location = values[3];
        int enrollmentCapacity = Integer.parseInt(values[4]);
        int enrollmentTotal = Integer.parseInt(values[5]);
        String instructor = values[6];
        String componentCode = values[7];

        return new CourseData(
            semesterId, subject,
            catalogNumber, location,
            enrollmentCapacity, enrollmentTotal,
            instructor, componentCode
        );
    }

    private void addToDepartment(List<Department> departmentList, CourseData courseData) {
        boolean departmentFound = false;
        // Course course = new Course(0, data); // TODO: Increment courseId

        for (Department department: departmentList) {
            if (department.getDepartmentName().equals(courseData.getSubjectName())) {
                // Department already exists, add it to the courseList of the Department
                department.addCourse(courseData);
                departmentFound = true;
                break;
            }
        }

        // A department for the subjectName does not exist yet, make one and add the course
        if (!departmentFound) {
            Department department = new Department(courseData.getSubjectName());

            department.addCourse(courseData);
            departmentList.add(department);
        }
    }
}
