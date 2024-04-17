package Model;

import java.util.List;

public interface Observer {
    // Used to change states of certain classes and log it.
    void changedState(String deptId, long courseId);
    List<String> getEvents();
}
