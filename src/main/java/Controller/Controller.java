package Controller;

import Model.DumpWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("dump-model")
    private void dumpModel() {
        DumpWriter dump = new DumpWriter();
    }
}
