package ac.ke.rondavels.marverick.admin;

import ac.ke.rondavels.marverick.bookings.booking;
import ac.ke.rondavels.marverick.bookings.bookingServiceInter;
import ac.ke.rondavels.marverick.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RestController

public class adminController {
    private final bookingServiceInter bookingServiceInter;
    private  final EmailServiceImpl emailService;



    @Autowired
    public adminController(bookingServiceInter bookingServiceInter, EmailServiceImpl emailService) {
        this.bookingServiceInter = bookingServiceInter;
        this.emailService = emailService;
    }



    @GetMapping("/_/")
    public ModelAndView admin() {
        List<booking> bookings = bookingServiceInter.findAllBookings();
        List <booking> upcomingBookings = bookingServiceInter.findUpcomingBookings();
        System.out.println("upcoming bookings" + upcomingBookings);
        System.out.println("all bookings" + bookings);
        ModelAndView modelAndView = new ModelAndView("adminTemplates/admin");
       modelAndView.addObject("bookings", bookings).addObject("upcomingBookings", upcomingBookings);
        return modelAndView;

    }

}
