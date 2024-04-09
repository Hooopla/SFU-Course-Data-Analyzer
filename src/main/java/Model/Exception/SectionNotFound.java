package Model.Exception;

public class SectionNotFound extends RuntimeException{
    public SectionNotFound() {
        super();
    }
    public SectionNotFound(String message) {
        super(message);
    }
}
