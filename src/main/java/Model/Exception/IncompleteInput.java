package Model.Exception;

public class IncompleteInput extends RuntimeException{
    public IncompleteInput() {
        super();
    }

    public IncompleteInput(String message) {
        super(message);
    }
}
