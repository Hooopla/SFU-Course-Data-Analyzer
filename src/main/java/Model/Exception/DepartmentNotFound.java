package Model.Exception;

public class DepartmentNotFound extends RuntimeException{
    public DepartmentNotFound() {
        super();
    }

    public DepartmentNotFound(String message) {
        super(message);
    }
}
