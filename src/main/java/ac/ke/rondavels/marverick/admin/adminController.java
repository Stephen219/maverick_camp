package ac.ke.rondavels.marverick.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class adminController {

    @GetMapping("/_/")
    public String admin(){
        return "adminTemplates/admin";
    }

}
