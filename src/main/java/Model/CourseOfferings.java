package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a list of all Past & Current Offerings of the course
 * FALL 2012 SPRING 2013 SUMMER 2018
 */

public class CourseOfferings {
    private long courseOfferingId;
    private String location;
    private String instructors;
    private String term;
    private int semesterCode;
    private int year;
    private List<Section> sectionList;

    // Constructors
    public CourseOfferings(long courseOfferingId, CourseData data) {
        this.courseOfferingId = courseOfferingId;
        this.location = data.getLocation();
        this.instructors = data.getInstructor();
        this.term = calculateTerm(data.getSemesterId());
        this.semesterCode = data.getSemesterId();
        this.year = calculateYear(data.getSemesterId());
        this.sectionList = new ArrayList<>();
    }

    // Add Section
    public void addSection(CourseData data) {
        int id = sectionList.size() + 1;
        Section newSection = new Section(id, data);
        sectionList.add(newSection);
        sortSections();
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
        String semesterIdYear = String.valueOf(semesterId);
        semesterIdYear = String.valueOf(
                (2000 * Integer.parseInt(semesterIdYear.substring(0, 1)))
                        + Integer.parseInt(semesterIdYear.substring(1, 3))
        );
        return Integer.parseInt(semesterIdYear);
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

    // Sort our Sections
    private void sortSections() {
        sectionList.sort((one, two) -> one.getType().compareTo(two.getType()));
    }


    public long getCourseOfferingId() {
        return courseOfferingId;
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

    public int getSemesterCode() {
        return semesterCode;
    }

    public int getYear() {
        return year;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }
}
