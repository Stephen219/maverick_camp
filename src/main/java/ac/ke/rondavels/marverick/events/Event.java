package ac.ke.rondavels.marverick.events;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    private String description;
    private String location;
    private String itinerary;
    private Long cost;
    @Future(message = "Date must be in the future")
    private LocalDateTime startDateTime;
    @Future(message = "Date must be in the future")
    private LocalDateTime endDateTime;
    private String image;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int responders = 0;


}
