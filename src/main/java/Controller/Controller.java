package Controller;

import Model.*;
import Model.Exception.CourseNotFound;
import Model.Exception.CourseOfferingsNotFound;
import Model.Exception.DepartmentNotFound;
import Model.Exception.SectionNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private List<Department> departmentList = new ArrayList<>();
    private Authors authors = new Authors();
    ModelLoader loader = new ModelLoader("data/course_data_2018.csv", departmentList);

    // About Us Page | @GetMapping
    @GetMapping("/about")
    public Authors getAboutPage() {
        return authors;
    }


    // Get List of Departments | @GetMapping
    // Comments : Works!
    @GetMapping("/departments")
    public List<Department> getDepartmentList() {
        return departmentList;
    }
    // Department -> Get CourseList | @GetMapping
    // Comment: Works!
    @GetMapping("/departments/{departmentName}/courses")
    public List<Course> getDepartment(@PathVariable("departmentName") String departmentName) {
        for (Department department : departmentList) {
            if (department.getDepartmentName().trim().equals(departmentName)) {
                return department.getCourseList();
            }
        }
        throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
    }

    // Get Total Amount of Students in that department each Semester | @GetMapping
    // Comment: I need to watch the video again as I am not sure if this is going to be used in the graph or not.

    // Department -> Course -> Get CourseOfferingsList | @GetMapping
    // Comment: Works!
    @GetMapping("/departments/{departmentName}/courses/{catalogNumber}/offerings")
    public List<CourseOfferings> getCourseOfferings(
            @PathVariable("departmentName") String departmentName,
            @PathVariable("catalogNumber") String catalogNumber) {
        boolean DepartmentFound = false;
        for (Department department : departmentList) {
            if (department.getDepartmentName().trim().equals(departmentName)) {
                DepartmentFound = true;
                for (Course course : department.getCourseList()) {
                    if (course.getCatalogNumber().trim().equals(catalogNumber)) {
                        return course.getCourseOfferingsList();
                    }
                }
            }
        }
        if (DepartmentFound == false) {
            throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
        }
        throw new CourseNotFound("Department: " + departmentName + " Course: " + catalogNumber + " was not retrieved.");
    }

    // Department -> Course -> CourseOfferings -> Get SectionList | @GetMapping
    // Comment: Works!!
    @GetMapping("/departments/{departmentName}/courses/{catalogNumber}/offerings/{courseOfferingsId}/sections")
    public List<Section> getSection(
            @PathVariable("departmentName") String departmentName,
            @PathVariable("catalogNumber") String catalogNumber,
            @PathVariable("courseOfferingsId") long courseOfferingId) {
        boolean DepartmentFound = false;
        boolean CourseFound = false;
        for (Department department : departmentList) {
            if (department.getDepartmentName().trim().equals(departmentName)) {
                DepartmentFound = true;
                for (Course course : department.getCourseList()) {
                    if (course.getCatalogNumber().trim().equals(catalogNumber)) {
                        CourseFound = true;
                    }
                    for (CourseOfferings courseOfferings :  course.getCourseOfferingsList()) {
                        if (courseOfferings.getCourseOfferingsId() == courseOfferingId) {
                            return courseOfferings.getSectionList();
                        }
                    }
                }
            }
        }
        if (DepartmentFound == false) {
            throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
        }
        else if (CourseFound == false) {
            throw new CourseNotFound("Department: " + departmentName + " Course: " + catalogNumber + " was not retrieved.");
        }
        else {
            throw new CourseOfferingsNotFound("Department: " + departmentName + "Course: " + catalogNumber + "CourseOfferings: " + courseOfferingId + " was not retrieved.");
        }
    }

    // Department -> Course -> CourseOfferings -> SectionList -> Section| @GetMapping
    // Comment: Works!!
    @GetMapping("/departments/{departmentName}/courses/{catalogNumber}/offerings/{courseOfferingsId}/sections/{sectionId}")
    public Section getSection(
            @PathVariable("departmentName") String departmentName,
            @PathVariable("catalogNumber") String catalogNumber,
            @PathVariable("courseOfferingsId") long courseOfferingId,
            @PathVariable("sectionId") long sectionId) {
        boolean DepartmentFound = false;
        boolean CourseFound = false;
        boolean CourseOfferingsFound = false;
        for (Department department : departmentList) {
            if (department.getDepartmentName().trim().equals(departmentName)) {
                DepartmentFound = true;
                for (Course course : department.getCourseList()) {
                    if (course.getCatalogNumber().trim().equals(catalogNumber)) {
                        CourseFound = true;
                    }
                    for (CourseOfferings courseOfferings :  course.getCourseOfferingsList()) {
                        if (courseOfferings.getCourseOfferingsId() == courseOfferingId) {
                            CourseOfferingsFound = true;
                            for(Section section : courseOfferings.getSectionList()) {
                                if(section.getSectionId() == sectionId) {
                                    return section;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (DepartmentFound == false) {
            throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
        }
        else if (CourseFound == false) {
            throw new CourseNotFound("Department: " + departmentName + " Course: " + catalogNumber + " was not retrieved.");
        }
        else if(CourseOfferingsFound == false){
            throw new CourseOfferingsNotFound("Department: " + departmentName + "Course: " + catalogNumber + "CourseOfferings: " + courseOfferingId + " was not retrieved.");
        }
        else {
            throw new SectionNotFound("Section Not Found.");
        }
    }

    // HELLO ALEX IF U SEE THIS ANYTHING BELOW THIS IS FUNCTION THAT WE MAY NEED TO MAKE? I just need to rewatch the video or if u know u can get rid of it as needed.
    // AddCourseOffering??
    // AddCourse??
    // AddSection??

    // Get Dump Model | @GetMapping
    // Comment: Works Great!
    @GetMapping("dump-model")
    private void printToConsole() {
        DumpWriter.dumpModel(departmentList);
    }
}
