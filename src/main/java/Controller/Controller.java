package Controller;

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
    private void dumpModel() {
        List<Department> departmentList = new ArrayList<>();
        DumpWriter dump = new DumpWriter();

        ModelLoader loader = new ModelLoader("data/course_data_2022.csv/", departmentList);
    }
}
