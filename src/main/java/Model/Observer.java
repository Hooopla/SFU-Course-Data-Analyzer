package Model;

public interface Observer {

    // Used to change states of certain classes and log it.
    void changedState(CourseOfferings offerings, CourseData data);
}
