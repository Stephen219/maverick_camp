package ac.ke.rondavels.marverick.blogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    private Long blogId;
    private String comment;
    private String createdAt;



    public Comment(Long blogId, String comment, LocalDateTime createdAt) {
        this.blogId = blogId;
        this.comment = comment;
        this.createdAt = formatDateTime(createdAt);
    }

    // Method to format LocalDateTime to String
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mma");
        return dateTime.format(formatter);
    }


}