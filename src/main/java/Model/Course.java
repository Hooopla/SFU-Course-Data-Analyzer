package Model;

import java.util.ArrayList;
import java.util.List;

public class Course implements Subject{
    private long courseId;
    private String catalogNumber;

    private List<CourseOfferings> courseOfferingsList;
    private List<Observer> observers;

    public Course(long courseId, CourseData data) {
        this.courseId = courseId;
        this.catalogNumber = data.getCatalogNumber();
        this.courseOfferingsList = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(CourseOfferings offerings, CourseData data) {
        observers.forEach(obs -> obs.changedState(offerings, data));
    }
}
