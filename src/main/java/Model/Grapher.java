package Model;

public class Grapher {
    private int semesterCode;
    private int totalCoursesTaken;

    public Grapher(int semesterCode, int totalCoursesTaken) {
        this.semesterCode = semesterCode;
        this.totalCoursesTaken = totalCoursesTaken;
    }

    public int getSemesterCode() {
        return semesterCode;
    };

    public int getTotalCoursesTaken() {
        return totalCoursesTaken;
    };

    @Override
    public String toString() {
        return "Grapher{" +
                "semesterCode=" + semesterCode +
                ", seatsTaken=" + totalCoursesTaken +
                '}';
    }
}
