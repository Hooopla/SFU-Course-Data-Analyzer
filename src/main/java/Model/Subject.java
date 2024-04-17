package Model;

/**
 * Used so we can observe the classes that implements this
*/
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String deptId, long courseId);
}
