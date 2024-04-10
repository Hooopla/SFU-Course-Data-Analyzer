package Controller;

import Model.CourseOfferings;
import Model.Department;
import Model.DumpWriter;
import Model.ModelLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("dump-model")
    private void printToConsole() {
        List<Department> departmentList = new ArrayList<>();
        ModelLoader loader = new ModelLoader("data/small_data.csv", departmentList);

        DumpWriter.dumpModel(departmentList);
    }
}
