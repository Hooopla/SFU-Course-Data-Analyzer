package Model;

public class Grapher {
    private int semesterCode;
    private int seatsTaken;

    public Grapher(int semesterCode, int seatsTaken) {
        this.semesterCode = semesterCode;
        this.seatsTaken = seatsTaken;
    }

    public int getSemesterCode() {
        return semesterCode;
    };

    public int getSeatsTaken() {
        return seatsTaken;
    };

    @Override
    public String toString() {
        return "Grapher{" +
                "semesterCode=" + semesterCode +
                ", seatsTaken=" + seatsTaken +
                '}';
    }
}
