package Model;

/**
 * This class contains information like our names and the name of the app it-self. :D
 */

public class API {
    private String appName = "Course Planner Application";
    private String authorName = "Gabriel B. & Alex V.";

    public API(String appName, String authorName) {
        this.appName = appName;
        this.authorName = authorName;
    }
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
