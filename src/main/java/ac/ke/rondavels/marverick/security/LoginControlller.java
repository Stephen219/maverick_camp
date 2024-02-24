package ac.ke.rondavels.marverick.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class LoginControlller {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
