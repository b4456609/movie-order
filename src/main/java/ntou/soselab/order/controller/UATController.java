package ntou.soselab.order.controller;

import ntou.soselab.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bernie on 2017/3/26.
 */
@RestController
public class UATController {
    private final OrderRepository orderRepository;

    @Autowired
    public UATController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @DeleteMapping("/reset")
    public void resetDB() {
        orderRepository.deleteAll();
    }
}