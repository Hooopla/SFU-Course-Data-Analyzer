package Model;

import Model.Exception.CourseOfferingsNotFound;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * This class is responsbile for a class itself like CMPT 120.
 *
 */

public class Course implements Subject {
    private long courseId; // <-- Unique quantifier code for this Course
    private String catalogNumber;
    private List<CourseOfferings> courseOfferingsList;
    private List<Observer> observerList;

    public Course(long courseId, CourseData data) {
        this.courseId = courseId;
        this.catalogNumber = data.getCatalogNumber();
        this.courseOfferingsList = new ArrayList<>();
        this.observerList = new ArrayList<>();
    }


    // Adding
    /*
       We want to check if the course exists.
       If it already does exsists this means we just want to add a new section!

       If it does not exsists this means we want to create a whole new course create a section for that course then add it to the list of courses we offer.
     */
    public void addCourseOffering(CourseData courseData) {
        boolean courseExists = false;
        notifyObservers(courseData.getSubjectName(), courseId);

        for (CourseOfferings currentOffering : courseOfferingsList) {
            if (currentOffering.getSemesterCode() == courseData.getSemesterId() && Objects.equals(currentOffering.getInstructors(), courseData.getInstructor())) {
                courseExists = true;
                currentOffering.addSection(courseData);
                break;
            }
        }

        if (!courseExists) {
            long id = courseOfferingsList.size() + 1;
            CourseOfferings newOfferedCourse = new CourseOfferings(id, courseData);
            newOfferedCourse.addSection(courseData);
            courseOfferingsList.add(newOfferedCourse);
            sortCourseOfferings();
        }
        // Create an CourseOffering object for the observer
        //CourseOfferings newCourseOfferingsObserver = new CourseOfferings(courseData);
        // NOTIFY OBSERVERS HERE
        //notifyObservers(newCourseOfferingsObserver, courseData);
    }

    public CourseOfferings findCourseOfferings(CourseData courseData) {
        for (CourseOfferings currentCourseOfferings : courseOfferingsList) {
            if (currentCourseOfferings.getSemesterCode() == courseData.getSemesterId()) {
                return currentCourseOfferings;
            }
        }
        throw new CourseOfferingsNotFound("Department " + courseData.getSubjectName()
                + " Course " + courseData.getCatalogNumber()
                + " Semester Offered " + courseData.getSemesterId()
                + " course offering not found.");
    }

    private void sortCourseOfferings() {
        courseOfferingsList.sort(Comparator.comparing(o -> o.getSemesterCode() + o.getLocation()));
    }

    public int getTotalEnrollmentInSemester(int semesterId) {
        int totalEnrollmentInSemester = 0;
        for (CourseOfferings currentCourseOfferings : courseOfferingsList) {
            if (currentCourseOfferings.getSemesterCode() == semesterId) {
                if (currentCourseOfferings.getSemesterCode() == semesterId) {
                    totalEnrollmentInSemester += currentCourseOfferings.getTotalEnrollments();
                }
            }
        }
        return totalEnrollmentInSemester;
    }

    @Override
    public void addObserver(WatcherInformation observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(WatcherInformation observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String deptId, long courseId) {
        observerList.forEach(obs -> obs.changedState(deptId, courseId));
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public List<CourseOfferings> getCourseOfferingsList() {
        return courseOfferingsList;
    }

    public List<Observer> getObserverList() {
        return observerList;
    }
}
