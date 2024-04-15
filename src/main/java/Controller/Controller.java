package Controller;

import Model.*;
import Model.Exception.DepartmentNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private List<Department> departmentList = new ArrayList<>();
    private Authors authors = new Authors();

    // About Us Page | @GetMapping
    @GetMapping("/about")
    public Authors getAboutPage() {
        return authors;
    }


    // Get List of Departments | @GetMapping
    @GetMapping("/departments")
    public List<Department> getDepartmentList() {
        return departmentList;
    }
    // Get One Specific Department | @GetMapping
    // Comment: Not sure if this works
    @GetMapping("/departments/{departmentName}/courses")
    public List<Course> getDepartment(@PathVariable("departmentName") String departmentName) {
        for (Department department : departmentList) {
            if(department.getDepartmentName().equals(departmentName)) {
                return department.getCourseList();
            }
        }
        throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
    }

    // Get Total Amount of Students in that department each Semester | @GetMapping
    // Comment: I need to watch the video again as I am not sure if this is going to be used in the graph or not.

    // Get List of Course | @GetMapping
    // Comment:

    // Get One Specific Course | @GetMapping
    // Comment:

    // Get List of Course Offering | @GetMapping
    // Comment:

    // Get One Specific Course Offering | @GetMapping
    // Comment:


    // Get List of Sections @GetMapping | @GetMapping
    // Comment:

    // Get One Specific Section @GetMapping | @GetMapping
    // Comment:

    // HELLO ALEX IF U SEE THIS ANYTHING BELOW THIS IS FUNCTION THAT WE MAY NEED TO MAKE? I just need to rewatch the video or if u know u can get rid of it as needed.
    // AddCourseOffering??
    // AddCourse??
    // AddSection??

    // Get Dump Model | @GetMapping
    // Comment: Works Great!
    @GetMapping("dump-model")
    private void printToConsole() {
        ModelLoader loader = new ModelLoader("data/course_data_2018.csv", departmentList);
        DumpWriter.dumpModel(departmentList);
    }
}
