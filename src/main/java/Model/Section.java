package Model;

/**
 * This class is responsible for like the different LEC and LABS
 */

public class Section {
    private long sectionId;
    private String type;
    private int enrollmentMax;
    private int enrollmentTotal;

    public Section(long sectionId, CourseData data) {
        this.sectionId = sectionId;
        this.type = data.getComponent();
        this.enrollmentMax = data.getEnrollmentCap();
        this.enrollmentTotal = data.getEnrollmentTotal();
    }

    public long getSectionId() {
        return sectionId;
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
