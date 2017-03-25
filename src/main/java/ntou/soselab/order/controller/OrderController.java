package ntou.soselab.order.controller;

import ntou.soselab.order.controller.dto.BookDTO;
import ntou.soselab.order.model.Order;
import ntou.soselab.order.repository.OrderRepository;
import ntou.soselab.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/book")
    public Order bookTickets(@RequestBody BookDTO bookDTO) {
        return orderService.bookTickets(bookDTO);
    }

    @GetMapping("/user/{userId}")
    public List<Order> checkOrders(@PathVariable("userId") String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @PostMapping("/tickets/{orderId}")
    public Order pickUpTickets(@PathVariable("orderId") String orderId) {
        return orderService.pickUpTickets(orderId);
    }
}
