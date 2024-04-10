package Model;

import Model.Exception.CourseOfferingsNotFound;
import Model.Exception.SectionNotFound;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of all Past & Current Offerings of the course
 * FALL 2012 SPRING 2013 SUMMER 2018
 */

public class CourseOfferings {
    private long courseOfferingsId;
    //private SpecificCourseOffering course;
    private String location;
    private String instructors;
    private String term;
    private int semesterId;
    private int year;
    private List<Section> sectionList;

    // Constructors
    public CourseOfferings(long courseId, long courseOfferingsId, CourseData data) {
        this.courseOfferingsId = courseOfferingsId;
        this.location = data.getLocation();
        this.instructors = data.getInstructor();
        this.term = calculateTerm(data.getSemesterId());
        this.semesterId = data.getSemesterId();
        this.year = calculateYear(data.getSemesterId());
        this.sectionList = new ArrayList<>();
    }

    // For Observer Constructor
    // 0 - Will be designated for Courses that are being watched by an observer.
    public CourseOfferings(CourseData courseData) {
        this.courseOfferingsId = 0;
        //this.course = new SpecificCourseOffering(courseData.getCatalogNumber(), courseOfferingsId);
        this.location = courseData.getLocation();
        this.instructors = courseData.getInstructor();
        this.term = calculateTerm(courseData.getSemesterId());
        this.year = calculateYear(courseData.getSemesterId());
        this.sectionList = new ArrayList<>();
    }

    // Add Section
    public void addSection(CourseData data) {

        boolean sectionExists = false;
        for (Section currentSection : sectionList) {
            if (currentSection.getType().equals(data.getComponent()) && currentSection.getEnrollmentTotal() == data.getEnrollmentTotal()
                    && currentSection.getEnrollmentMax() == currentSection.getEnrollmentMax()) {
                sectionExists = true;
                break;
            }
        }
        if (!sectionExists) {
            Section newSection = new Section(data);
            sectionList.add(newSection);
            sortSections();
        }
    }

    // Calculate which term
    private String calculateTerm(int semesterId){
        String semesterCode = Integer.toString(semesterId);
        int termCode = Integer.parseInt(String.valueOf(semesterCode.charAt(3)));
        if (termCode == 1) {
            return "Spring";
        }
        else if (termCode == 4) {
            return "Summer";

        }
        else if (termCode == 7) {
            return "Fall";
        }
        else {
            return "ERROR TERM NOT FOUND";
        }
    }

    // Calculate which year
    private int calculateYear(int semesterId) {
        return (int) (semesterId / Math.pow(10, Math.floor(Math.log10(semesterId)) - 1));
    }

    // Get total enrollments
    public int getTotalEnrollments() {
        int totalEnrollments = 0;
        for (Section currentSection : sectionList) {
            if(currentSection.getType().equals("LEC")) {
                totalEnrollments += currentSection.getEnrollmentTotal();
            }
        }
        return totalEnrollments;
    }

    // Find Section
    public Section findSection(CourseData courseData) {
        for (Section currentSection : sectionList) {
            if (currentSection.getType().equals(courseData.getComponent()) && currentSection.getEnrollmentTotal() == courseData.getEnrollmentTotal()
                    && currentSection.getEnrollmentMax() == currentSection.getEnrollmentMax()) {
                return currentSection;
            }
        }
        throw new SectionNotFound("Department: " + courseData.getSubjectName()
                + " Course " + courseData.getCatalogNumber()
                + " Semester Offered " + courseData.getSemesterId()
                + " Section " + courseData.getComponent()
                + " "
                + courseData.getEnrollmentTotal()
                + "/"
                + courseData.getEnrollmentCap()
                + " Section not found :(" );
    }

    // Sort our Sections
    private void sortSections() {
        sectionList.sort((one, two) -> one.getType().compareTo(two.getType()));
    }


    public long getCourseOfferingsId() {
        return courseOfferingsId;
    }

    public String getLocation() {
        return location;
    }

    public String getInstructors() {
        return instructors;
    }

    public String getTerm() {
        return term;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public int getYear() {
        return year;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }
}
