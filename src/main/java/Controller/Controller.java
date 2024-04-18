package Controller;

import Model.*;
import Model.DTO.APICourseOfferingDTO;
import Model.DTO.WatcherDTO;
import Model.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Controller {
    private final List<Department> departmentList = new ArrayList<>();
    private final Authors authors = new Authors();
    ModelLoader loader = new ModelLoader("data/course_data_2018.csv", departmentList);
    List<WatcherDTO> watcherDTOList = new ArrayList<>();

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
            if (department.getName().trim().equals(departmentName)) {
                return department.getCourseList();
            }
        }
        throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
    }

    // Get Total Amount of Students in that department each Semester | @GetMapping
    // Comment: I need to watch the video again as I am not sure if this is going to be used in the graph or not.

    // Department -> Course -> Get CourseOfferingsList | @GetMapping
    // Comment: Works!
    @GetMapping("/departments/{departmentName}/courses/{courseId}/offerings")
    public List<CourseOfferings> getCourseOfferings(
            @PathVariable("departmentName") String departmentName,
            @PathVariable("courseId") long courseId) {
        boolean DepartmentFound = false;
        for (Department department : departmentList) {
            if (department.getName().trim().equals(departmentName)) {
                DepartmentFound = true;
                for (Course course : department.getCourseList()) {
                    if (course.getCourseId() == courseId) {
                        return course.getCourseOfferingsList();
                    }
                }
            }
        }
        if (!DepartmentFound) {
            throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
        }
        throw new CourseNotFound("Department: " + departmentName + " CourseId: " + courseId + " was not retrieved. ");
    }

    // Department -> Course -> CourseOfferings -> Get SectionList | @GetMapping
    // Comment: Works!!
    @GetMapping("/departments/{departmentName}/courses/{courseId}/offerings/{courseOfferingsId}")
    public List<Section> getSections(
            @PathVariable("departmentName") String departmentName,
            @PathVariable("courseId") long courseId,
            @PathVariable("courseOfferingsId") long offeringId) {
        boolean DepartmentFound = false;
        boolean CourseFound = false;
        for (Department department : departmentList) {
            if (department.getName().trim().equals(departmentName)) {
                DepartmentFound = true;
                for (Course course : department.getCourseList()) {
                    if (course.getCourseId() == courseId) {
                        CourseFound = true;
                    }
                    for (CourseOfferings courseOfferings :  course.getCourseOfferingsList()) {
                        if (courseOfferings.getCourseOfferingId() == offeringId) {
                            return courseOfferings.getSectionList();
                        }
                    }
                }
            }
        }
        if (!DepartmentFound) {
            throw new DepartmentNotFound("Department: " + departmentName + " was not retrieved.");
        }
        if (!CourseFound) {
            throw new CourseNotFound("Department: " + departmentName + " CourseId: " + courseId + " was not retrieved.");
        }
        throw new CourseOfferingsNotFound("Department: " + departmentName + " CourseId: " + courseId + " CourseOfferings: " + offeringId + " was not retrieved.");
    }

    @PostMapping("addoffering")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void addCourseOffering(@RequestBody APICourseOfferingDTO dto) {
        if (String.valueOf(dto.getSemester()).length() != 4) {
            throw new IncompleteInput("Semester must be 4 digits.");
        } else if (dto.getLocation() == null) {
            throw new IncompleteInput("Location required.");
        } else if (dto.getEnrollmentCap() < 0 || dto.getEnrollmentTotal() < 0) {
            throw new IncompleteInput("Enrollment Total must be 0 or greater");
        }else {
            try {
                Integer.parseInt(dto.getComponent());
                throw new IncompleteInput("The provided component is an integer, it should be a string.");
            } catch (NumberFormatException e) {
                // If parsing fails, do nothing as the String is an intended String.
            }
        }

        CourseData data = dto.getCourseData();
        for (Department department: departmentList) {
            if (department.getName().equals(dto.getSubjectName())) {
                department.addCourse(data);
            }
        }
    }

    @GetMapping("watchers")
    private List<WatcherDTO> getWatchers() {
        watcherDTOList.clear();

        for (Department department: departmentList) {
            for (Course course: department.getCourseList()) {
                for (Observer watcher: course.getObserverList()) {
                    WatcherDTO dto = new WatcherDTO(
                            watcherDTOList.size() + 1,
                            department,
                            course,
                            watcher.getEvents()
                    );
                    watcherDTOList.add(dto);
                }
            }
        }
        return watcherDTOList;
    }

    @PostMapping("watchers")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void createWatcher(@RequestBody Map<String, Object> body) {
        String bodyDep = (String) body.get("deptId");
        long bodyCourseId = Long.parseLong(body.get("courseId").toString());
        WatcherInformation watcher = new WatcherInformation(
                bodyDep,
                bodyCourseId
        );

        for (Department department: departmentList) {
            if (department.getName().equals(bodyDep)) {
                for (Course course: department.getCourseList()) {
                    if (course.getCourseId() == bodyCourseId) {
                        System.out.println("Adding Observer for " + course.getCourseId() + " " + bodyCourseId);
                        course.addObserver(watcher);
                    }
                }
            }
        }
    }

    // Grapher Function | @GetMapping
    // Comments: In progress
    @GetMapping("/stats/students-per-semester")
    public List<Grapher> getGraph(@RequestParam(value = "deptId") String deptId) {
        for (Department department : departmentList) {
            if(department.getName().trim().equals(deptId)) {
                return department.getGraphData();
            }
        }
        throw new DepartmentNotFound("Department: " + deptId + " was not retrieved.");
    }


    // Get Dump Model | @GetMapping
    // Comment: Works Great!
    @GetMapping("dump-model")
    private void printToConsole() {
        DumpWriter.dumpModel(departmentList);
    }

    private Course getCourseFromDepartmentCourseId(String departmentName, long courseId) {
        for (Department department: departmentList) {
            if (department.getName().equals(departmentName)) {
                for (Course course: department.getCourseList()) {
                    if (course.getCourseId() == courseId) {
                        return course;
                    }
                }
            }
        }
        throw new CourseNotFound();
    }

    // Exception Handlers
    @ExceptionHandler(DepartmentNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String DepartmentNotFoundHandler(DepartmentNotFound error) {
        return error.getMessage();
    }

    @ExceptionHandler(CourseNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CourseNotFoundHandler(CourseNotFound error) {
        return error.getMessage();
    }

    @ExceptionHandler(CourseOfferingsNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CourseOfferingsNotFoundHandler(CourseOfferingsNotFound error) {
        return error.getMessage();
    }

    @ExceptionHandler(SectionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String SectionNotFoundHandler(SectionNotFound error) {
        return error.getMessage();
    }

    @ExceptionHandler(IncompleteInput.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String IncompleteInputHandler(IncompleteInput error) {
        return error.getMessage();
    }
}
