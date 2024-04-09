package Model.Exception;

public class CourseNotFound extends RuntimeException{
    public CourseNotFound() {
        super();
    }

    public CourseNotFound(String message) {
        super(message);
    }

}
