package Model.Exception;

/**
 * Indicates that the CourseOfferings was not found :(
 */

public class CourseOfferingsNotFound extends  RuntimeException {
    public CourseOfferingsNotFound() {
        super();
    }

    public CourseOfferingsNotFound(String message) {
        super(message);
    }
}
