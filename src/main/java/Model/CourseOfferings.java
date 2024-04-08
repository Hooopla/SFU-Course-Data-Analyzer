package Model;

import java.util.List;

public class CourseOfferings {
    private long courseOfferingsID;
    private String location;
    private String instructors;
    private String term;
    private int semesterCode;
    private int year;
    private List<Section> sectionList;

    // Constructors

    // Add Section

    // Calculate which term

    // Calculate which year

    // Get total enrollments

    // Find Section

    // Sort our Sections

    public long getCourseOfferingsID() {
        return courseOfferingsID;
    }

    public void setCourseOfferingsID(long courseOfferingsID) {
        this.courseOfferingsID = courseOfferingsID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstructors() {
        return instructors;
    }

    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(int semesterCode) {
        this.semesterCode = semesterCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }
}
