package Model;

public class CourseData {
    private int semester;
    private String subjectName; // Example <- CMPT...
    private String catalogNumber; // The course number so like 213
    private String location;
    private int enrollmentCap; // Enrollment Capacity
    private int enrollmentTotal; // The total amount of people for this section
    private String instructor; //
    private String component; // <- Lec / TUT, SEC,OPL

    public CourseData(int semesterId, String subject,
                      String catalogNumber, String location,
                      int enrollmentCapacity,
                      int enrollmentTotal,
                      String instructor,
                      String componentCode) {

        this.semester = semesterId;
        this.subjectName = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCap = enrollmentCapacity;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
        this.component = componentCode;

    }

    public CourseData() {}

    public String getCourseName() {
        return subjectName + " " + catalogNumber;
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

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getComponent() {
        return component;
    }

    public void increaseEnrollmentCapacity(int enrollmentCapacity){
        this.enrollmentCap = this.enrollmentCap + enrollmentCapacity;
    }

    public void increaseEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = this.enrollmentTotal + enrollmentTotal;
    }

    public void addInstructor(String add) {
        String newInstructor = this.instructor + ", " + add;
        this.instructor = newInstructor;
    }
}
