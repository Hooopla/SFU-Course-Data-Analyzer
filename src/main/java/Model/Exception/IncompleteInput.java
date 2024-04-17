package Model.Exception;

public class IncompleteInput extends RuntimeException{
    IncompleteInput() {
        super();
    }

    IncompleteInput(String message) {
        super(message);
    }
}
