package Model;

import java.util.List;
import java.util.Observer;

public class Course implements Subject{
    private long courseId;
    private String catalogNumber;

    private List<CourseOfferings> courseOfferingsList;
    private List<Observer> observers;

    public Course(long courseId, CourseData data) {

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
