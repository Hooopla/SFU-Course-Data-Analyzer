package Model;

/**
 * Used so we can observe the classes that implements this
*/
public interface Subject {
    void addObserver(WatcherInformation observer);
    void removeObserver(WatcherInformation observer);
    void notifyObservers(String deptId, long courseId);
}
