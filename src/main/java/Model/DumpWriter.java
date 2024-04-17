package Model;

import java.util.List;

/**
 * Prints courses to console, used for debugging and for the Dump Model button in about.html
 */
public class DumpWriter {
    public static void dumpModel(List<Department> departmentList) {
        for (Department dep: departmentList) {
            int courseCounter = 0;
            System.out.println("Department: " + dep.getName());

            for (Course course: dep.getCourseList()) {
                System.out.println(dep.getName() + " -> Course at index " + courseCounter + ": " + dep.getCourse(courseCounter).getCatalogNumber());
                List<CourseOfferings> offeringsList = dep.getCourse(courseCounter).getCourseOfferingsList();
                System.out.println("CourseOfferings data for Course");

                for (CourseOfferings offerings: offeringsList) {
                    System.out.println("    Year: " + offerings.getYear());
                    System.out.println("    Location: " + offerings.getLocation());
                    System.out.println("    Instructor: " + offerings.getInstructors());
                    List<Section> sectionList = offerings.getSectionList();
                    for(Section section : sectionList) {
                        System.out.println("        Type = " + section.getType() + ", Enrollment=" + section.getEnrollmentTotal() + "/" + section.getEnrollmentCap());
                    }
                }
                courseCounter += 1;
            }
        }
    }
}
