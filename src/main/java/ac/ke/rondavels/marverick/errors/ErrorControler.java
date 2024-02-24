package ac.ke.rondavels.marverick.errors;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControler implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "errors/404";
    }


    public String getErrorPath() {
        return "/error"; // Return the path for error handling
    }
}
