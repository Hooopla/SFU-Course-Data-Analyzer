package Model;

import java.util.Observer;

public class Course implements Subject{
    private long courseId;
    private String catalogNumber;


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
