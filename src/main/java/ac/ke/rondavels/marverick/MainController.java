package ac.ke.rondavels.marverick;

import ac.ke.rondavels.marverick.events.EventServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        return "about_us";
    }


    @GetMapping("/gallery")
    public ModelAndView gallery(){
        System.out.println(eventServiceInter.getAllImages());
        return new ModelAndView("gallery").addObject("images", eventServiceInter.getAllImages());
    }

    @GetMapping("/contact")
    public String contact_us(){
        return "contra";
    }


    @GetMapping("/data")
    public ResponseEntity<String> getJSONData() {
        String jsonData = loadJSONFromFile("data.json");
        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .body(jsonData);
    }
    private String loadJSONFromFile(String fileName) {
        try {
            Resource resource = new ClassPathResource("static/" + fileName);
            byte[] jsonDataBytes = Files.readAllBytes(Paths.get(resource.getURI()));
            return new String(jsonDataBytes);
        } catch (IOException e) {

            throw new RuntimeException("Failed to load JSON data from file: " + fileName);
        }
    }
}
