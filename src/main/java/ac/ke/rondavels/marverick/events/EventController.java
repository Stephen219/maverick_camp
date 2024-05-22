package ac.ke.rondavels.marverick.events;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

@Controller

public class EventController {
    @Autowired
    EventServiceInter eventServiceInter;

    @GetMapping("/events")
    public String getEvents(Model model) {
        List<Event> events = List.of(eventServiceInter.getAllEvents());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
        Map<Integer, String> formattedDates = events.stream()
                .collect(Collectors.toMap(
                        events::indexOf,
                        event -> event.getStartDateTime().format(formatter)
                ));
        model.addAttribute("events", events);
        model.addAttribute("formattedDates", formattedDates);

        return "events/events";
    }

    @GetMapping("/events/{id}")
    public ModelAndView getEvent( @PathVariable Long id){
        Event event = eventServiceInter.getEvent(id);
        int intCost = Math.toIntExact(event.getCost());
        LocalDateTime blogDate = event.getStartDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma");
        String formattedDate = blogDate.format(formatter);


        return new ModelAndView("events/eventDetails")
                .addObject("cost", intCost)
                . addObject("createdDate", event.getCreatedAt().format(formatter))
                .addObject("event", eventServiceInter.getEvent(id)).
                addObject("formattedDate", formattedDate);


    }



    @GetMapping("/add_event")
    public ModelAndView addOrEditEvent(@RequestParam(required = false) Long id) {
        Event event;
        if (id != null) {
            event = eventServiceInter.getEvent(id);
        } else {
            event = new Event();
        }
        System.out.println(event);
        System.out.println(event);
        System.out.println(event);
        System.out.println(event);
        System.out.println("System.out.println(event);bdjksjkbesjkeaks'pljkflds'kldeksfllhdlhudjkwoeikfdnfhu");

        ModelAndView modelAndView = new ModelAndView("events/addEvents");
        modelAndView.addObject("event", event);
        return modelAndView;
    }

    @PostMapping("/add_event")
    public ModelAndView addEvent(@Valid Event event, BindingResult bindingResult,@RequestParam(required = false) Long id
    ){
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("events/addEvents");
            modelAndView.addObject("event", event);
            return modelAndView;
        }
        if ((id != null)) {
            eventServiceInter.updateEvent(event);
        } else {
            eventServiceInter.addEvent(event);
        }
        return new ModelAndView("redirect:/events");
    }


    @GetMapping("/events/{id}/interested")
    public String interested(@PathVariable Long id, Model model) {
        model.addAttribute("event",eventServiceInter.getEvent(id)).addAttribute("date", eventServiceInter.getEvent(id).getStartDateTime().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma")));
        return "events/eventParticipants";
    }


    @PostMapping("/events/{id}/interested")
    public String interested(@PathVariable Long id, Model model, @RequestParam String attendee, @RequestParam String phone, @RequestParam String code, @RequestParam String message_name) {
        Event event = eventServiceInter.getEvent(id);
        model.addAttribute("event", event).addAttribute("date", event.getStartDateTime().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma")));
        eventServiceInter.addParticipant(id, attendee, code, phone, message_name);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public ModelAndView deleteEvent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            eventServiceInter.deleteEvent(id);
            redirectAttributes.addFlashAttribute("message", "Event deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Unable to delete the event");
        }
        return new ModelAndView("redirect:/events");
    }






}
