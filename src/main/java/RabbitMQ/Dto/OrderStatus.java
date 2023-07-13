package RabbitMQ.Dto;

import com.example.ventoBack.Entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {
    private Order order;
    private String status;
    private String message;
}
