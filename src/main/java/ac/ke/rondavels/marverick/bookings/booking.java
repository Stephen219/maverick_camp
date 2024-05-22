package ac.ke.rondavels.marverick.bookings;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class booking {
    private Long id;
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    @NotEmpty(message = "Phone number is required")

    private String phone;

    @NotEmpty(message = "Country is required")



    private String country;
    @NotNull(message = "Country code is required")
    private Long countryCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Checkin date is required")

    @Future(message = "Checkin date should be in the future")
    private LocalDate checkin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Checkout date is required")
    @Future(message = "Checkin date should be in the future")
    private LocalDate checkout;
    private String room;
    private  String mealPlan;
    private String adults;
    private String children;
    private String message;
    private String price;
    private String status;






    public booking(long id, String firstName, String lastName, String email, String phone, String country, LocalDate checkin, LocalDate checkout, String adults, String children, String room, String mealPlan, String message, String totalPrice, String status) {

      this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adults = adults;
        this.children = children;
        this.room = room;
        this.mealPlan = mealPlan;
        this.message = message;
        this.price = totalPrice;
        this.status = status;}
}
