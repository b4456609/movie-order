package ntou.soselab.movie.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookShowDTO {
    private String showId;
    private int tickets;
}
