package Model;

import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(CourseOfferings offerings, CourseData data) {
        observerList.forEach(obs -> obs.changedState(offerings, data));
    }
}
