package Model;

/**
 * This class is responsible for like the different LEC and LABS
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
