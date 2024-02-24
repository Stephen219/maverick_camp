package ac.ke.rondavels.marverick.blogs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Blog {
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "main body cannot be empty")
    private String content;
    private String description;
    private LocalDateTime dateCreated;
    private String image;
    private String formattedDate = null;


}
