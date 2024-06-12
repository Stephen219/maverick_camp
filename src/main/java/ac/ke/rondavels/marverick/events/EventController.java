package ac.ke.rondavels.marverick.events;

import ac.ke.rondavels.marverick.email.EmailService;
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
    @Autowired
    EmailService emailService;

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
    public String interested(@PathVariable Long id, Model model, @RequestParam String attendee, @RequestParam String phone, @RequestParam String code, @RequestParam String message_name , @RequestParam String email){
        Event event = eventServiceInter.getEvent(id);
        model.addAttribute("event", event).addAttribute("date", event.getStartDateTime().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mma")));
        eventServiceInter.addParticipant(id, attendee, code, phone, message_name,email);
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


    @GetMapping("event/{id}/interested")
    public ModelAndView specificEventParticipants(@PathVariable Long id){
        List<Map<String, Object>> allParticipants = eventServiceInter.getParticipantsForEvent(Math.toIntExact(id));
        List<Map<String, Object>> confirmedParticipants = allParticipants.stream().filter(participant -> (boolean) participant.get("is_confirmed")).toList();
        List<Map<String, Object>> unconfirmedParticipants = allParticipants.stream().filter(participant -> !(boolean) participant.get("is_confirmed")).toList();
        Event event = eventServiceInter.getEvent(id);
        return new ModelAndView("events/participants.html")
                .addObject("participants", unconfirmedParticipants).
                addObject("event", event)
                .addObject("confirmedParticipants", confirmedParticipants);
    }

    @GetMapping ("event/{id}/interested/{participantId}/confirm")
    public ModelAndView confirmParticipant(@PathVariable Long id, @PathVariable Long participantId){
        eventServiceInter.confirmParticipant(participantId);
        List<Map<String, Object>> allParticipants = eventServiceInter.getParticipantsForEvent(Math.toIntExact(id));
        List<Map<String, Object>> confirmedParticipants = allParticipants.stream().filter(participant -> (boolean) participant.get("is_confirmed")).toList();
        Map<String, Object> participant = allParticipants.stream().filter(part -> (int) part.get("id") == Math.toIntExact(participantId)).findFirst().get();

        System.out.println(participantId);
        String email = (String) participant.get("email");
        String name = (String) participant.get("attendee");
        String message = "Hello "+name+",\n\nYou have been confirmed to attend the event. We are looking forward to seeing you there.\n\nRegards,\nRondavels";
        emailService.sendSimpleMessage(email, "Confirmation for the event", message);

        System.out.println("Participant confirmed");
        return new ModelAndView("redirect:/event/"+id+"/interested");
    }






}
