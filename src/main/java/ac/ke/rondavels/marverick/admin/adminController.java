package ac.ke.rondavels.marverick.admin;

import ac.ke.rondavels.marverick.bookings.booking;
import ac.ke.rondavels.marverick.bookings.bookingServiceInter;
import ac.ke.rondavels.marverick.email.EmailServiceImpl;
import ac.ke.rondavels.marverick.events.Event;
import ac.ke.rondavels.marverick.events.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RestController

public class adminController {
    private final bookingServiceInter bookingServiceInter;
    private  final EmailServiceImpl emailService;
    private final EventServiceImpl eventServiceImpl;


    @Autowired
    public adminController(bookingServiceInter bookingServiceInter, EmailServiceImpl emailService, EmailServiceImpl emailServiceinter, EventServiceImpl eventServiceImpl) {
        this.bookingServiceInter = bookingServiceInter;
        this.emailService = emailService;
        this.eventServiceImpl = eventServiceImpl;
    }



    @GetMapping("/_/")
    public ModelAndView admin() {
        List<booking> bookings = bookingServiceInter.findAllBookings();
        List <booking> upcomingBookings = bookingServiceInter.findUpcomingBookings();
        List<Event> events = Arrays.asList(eventServiceImpl.getAllEvents());
        System.out.println("events" + events);

        for (Event event : events) {
            Long eventId = event.getId();
            List<Map<String, Object>> participants = eventServiceImpl.getParticipantsForEvent(Math.toIntExact(eventId));
            event.setResponders(participants.size());
        }



        System.out.println("upcoming bookings" + upcomingBookings);
        System.out.println("all bookings" + bookings);
        ModelAndView modelAndView = new ModelAndView("adminTemplates/admin");
       modelAndView.addObject("bookings", bookings).addObject("events", events)
               .addObject("upcomingBookings", upcomingBookings);
        return modelAndView;

    }






}
