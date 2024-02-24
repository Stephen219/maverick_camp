package ac.ke.rondavels.marverick.bookings;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        EUR, USD,KES;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}