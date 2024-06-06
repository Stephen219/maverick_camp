package ac.ke.rondavels.marverick.bookings;

import ac.ke.rondavels.marverick.StripeService;
import ac.ke.rondavels.marverick.email.EmailServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class BookingController {

    bookingServiceInter bookingServiceInter;
    EmailServiceImpl emailService;

    @Autowired
    public BookingController(bookingServiceInter bookingServiceInter, EmailServiceImpl emailService
    ) {
        this.bookingServiceInter = bookingServiceInter;
        this.emailService = emailService;

    }


    @GetMapping("/")
    public String test() {

        return "home";
    }

    @GetMapping("/book")
    public ModelAndView book() {
        ModelAndView modelAndView = new ModelAndView("booking");
        booking booking = new booking();
        modelAndView.addObject("booking", booking);
        return modelAndView;
    }

    @PostMapping("/book")
    public ModelAndView handleBooking(@Valid booking booking, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("booking");
            modelAndView.addObject("booking", booking);
            return modelAndView;
        }

        if (booking.getCheckin() != null && booking.getCheckout() != null) {
            System.out.println("we are before this line");
            if (booking.getCheckin().isAfter(booking.getCheckout())) {
                System.out.println("Checkin date is after checkout date");

                bindingResult.rejectValue("checkin", "date.invalid", "Check-in date cannot be after check-out date");
                ModelAndView modelAndView = new ModelAndView("booking");
                modelAndView.addObject("booking", booking);

                return modelAndView;
            } else if (booking.getCheckin().isEqual(booking.getCheckout())) {
                System.out.println("Checkin date is equal to checkout date ehe we are here  now");
                bindingResult.rejectValue("checkin", "date.invalid", "Check-in date cannot be the same as check-out date");
                ModelAndView modelAndView = new ModelAndView("booking");
                modelAndView.addObject("booking", booking);
                return modelAndView;
            }
        }
        if (booking.getPhone().startsWith("0"))
        {
            booking.setPhone(booking.getPhone().replaceFirst("^0+", ""));
        }
        booking.setPhone("+" + booking.getCountryCode() + booking.getPhone());
        booking.setStatus("Pending");
        booking.setPrice("1000");
        bookingServiceInter.saveBooking(booking);
        return new ModelAndView("redirect:/");
    }




    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }



    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }


    @PostMapping("/message")
    public ModelAndView getEnquiryAtTheFooter(@RequestParam("enquiry_email") String email, @RequestParam("message") String message, RedirectAttributes redirectAttributes){
        emailService.sendSimpleMessage("stephenkariuki838@gmail.com,", "Enquiry", message + " from " + email);
        bookingServiceInter.saveMessage(email, message);
        redirectAttributes.addFlashAttribute("message", "Your message has been sent successfully");
        return new ModelAndView("redirect:/");
    }


}
