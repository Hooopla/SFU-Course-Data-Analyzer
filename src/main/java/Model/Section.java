package Model;

/**
 * This is responsible for a certain section of a class. Example CMPT 120 has CMPT D100 D200 D300.
 * This class will only then handle section D200
 */

public class Section {

    private String type;
    private int enrollmentMax;
    private int enrollmentTotal;

    public Section(CourseData data) {
        this.type = data.getComponent();
        this.enrollmentMax = data.getEnrollmentCap();
        this.enrollmentTotal = data.getEnrollmentTotal();
    }

    public String getType() {
        return type;
    }

    public int getEnrollmentMax() {
        return enrollmentMax;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

}
