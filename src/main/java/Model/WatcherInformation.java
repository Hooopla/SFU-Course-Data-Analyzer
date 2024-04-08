package Model;

public class WatcherInformation implements Observer {
    private int eventCounter = 0;

    // Log the new event
    @Override
    public void changedState(CourseOfferings offerings, CourseData data) {
        eventCounter += 1;


    }

    public int getEventCounter() {
        return eventCounter;
    }
}
