package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Course implements Subject{
    private long courseId;
    private String catalogNumber;

    private List<CourseOfferings> courseOfferingsList;
    private List<Observer> observerList;

    public Course(long courseId, CourseData data) {
        this.courseId = courseId;
        this.catalogNumber = data.getCatalogNumber();
        this.courseOfferingsList = new ArrayList<>();
        this.observerList = new ArrayList<>();
    }


    public void addCourseOffering(CourseData courseData) {
        boolean courseExists = false;

        for (CourseOfferings offering : courseOfferingsList) {
            if (offering.getSemesterCode() == courseData.getSemester()) {
                courseExists = true;
                //
            }

        }

    }

    @Override
    public void addObserver(Observer observer) {
        

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(CourseOfferings offerings, CourseData data) {

    }
}
