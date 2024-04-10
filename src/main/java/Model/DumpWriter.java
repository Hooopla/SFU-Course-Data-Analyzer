package Model;

import java.util.List;

/**
 * Prints courses to console, used for debugging and for the Dump Model button in about.html
 */
public class DumpWriter {
    public static void dumpModel(List<Department> departmentList) {
        int counter = 0;
        for (Department dep: departmentList) {
            System.out.println("Department: " + dep.getDepartmentName());
            System.out.println(dep.getDepartmentName() + " -> Course at index " + counter + ": " + dep.getCourse(counter).getCatalogNumber());
            List<CourseOfferings> offeringsList = dep.getCourse(counter).getCourseOfferingsList();

            System.out.println("CourseOfferings data for Course at index " + counter + ": ");
            for (CourseOfferings offerings: offeringsList) {
                System.out.println("    Year: " + offerings.getYear());
                System.out.println("    Location: " + offerings.getLocation());
                System.out.println("    Instructor: " + offerings.getInstructors());
                List<Section> sectionList = offerings.getSectionList();
                for(Section section : sectionList) {
                    System.out.println("        Type = " + section.getType() + ", Enrollment=" + section.getEnrollmentTotal() + "/" + section.getEnrollmentMax());
                }
            }
        }
    }
}
