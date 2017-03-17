package ntou.soselab.order.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Order {
    @Id
    private String id;
    @Indexed
    private String userId;
    private String showId;
    private long timestamp;
    private boolean isPickUp;
    private int ticket;

    @Tolerate
    Order(){}
}
