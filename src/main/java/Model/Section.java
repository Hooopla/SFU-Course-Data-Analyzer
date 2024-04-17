package Model;

/**
 * This class is responsible for like the different LEC and LABS
 */

public class Section {
    private long sectionId;
    private String type;
    private int enrollmentCap;
    private int enrollmentTotal;

    public Section(long sectionId, CourseData data) {
        this.sectionId = sectionId;
        this.type = data.getComponent();
        this.enrollmentCap = data.getEnrollmentCap();
        this.enrollmentTotal = data.getEnrollmentTotal();
    }

    public long getSectionId() {
        return sectionId;
    }
    public String getType() {
        return type;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

}
