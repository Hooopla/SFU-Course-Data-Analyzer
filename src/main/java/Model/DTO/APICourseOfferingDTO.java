package Model.DTO;

import Model.Course;
import Model.CourseData;
import Model.Department;
import Model.Exception.IncompleteInput;

import java.util.List;

public class APICourseOfferingDTO {
    private int semester;
    private String subjectName;
    private String catalogNumber;
    private String location;
    private int enrollmentCap;
    private String component;
    private int enrollmentTotal;
    private String instructor;

    public APICourseOfferingDTO (
        int semester,
        String subjectName,
        String catalogNumber,
        String location,
        int enrollmentCap,
        String component,
        int enrollmentTotal,
        String instructor
    )

    {
        this.semester = semester;
        this.subjectName = subjectName;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
        this.component = component;
    }

    public CourseData getCourseData() {
        return new CourseData(
            this.semester,
            this.subjectName,
            this.catalogNumber,
            this.location,
            this.enrollmentCap,
            this.enrollmentTotal,
            this.instructor,
            this.component
        );
    }

    public int getSemester() {
        return semester;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public String getComponent() {
        return component;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public String getInstructor() {
        return instructor;
    }
}
