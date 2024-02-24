package ac.ke.rondavels.marverick;

import ac.ke.rondavels.marverick.events.EventServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class MainController {

    EventServiceInter eventServiceInter;

    @Autowired
    public MainController(EventServiceInter eventServiceInter) {
        this.eventServiceInter = eventServiceInter;
    }


    @GetMapping("/rooms")
    public String rooms(){
        return "rooms";
    }

    @GetMapping("/about_us")
    public String about_us(){
        return "test";
    }


    @GetMapping("/gallery")
    public ModelAndView gallery(){
        System.out.println(eventServiceInter.getAllImages());
        return new ModelAndView("gallery").addObject("images", eventServiceInter.getAllImages());
    }
}
