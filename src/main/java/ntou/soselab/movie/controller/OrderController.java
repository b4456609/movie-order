package ntou.soselab.movie.controller;

import ntou.soselab.movie.controller.dto.BookDTO;
import ntou.soselab.movie.model.Order;
import ntou.soselab.movie.repository.OrderRepository;
import ntou.soselab.movie.service.OrderService;
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
    public void bookTickets(@RequestBody BookDTO bookDTO) {
        orderService.bookTickets(bookDTO);
    }

    @GetMapping("/orders/user/{userId}")
    public List<Order> checkOrders(@PathVariable("userId") String userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @PostMapping("tickets/{orderId}")
    public Order pickUpTickets(@PathVariable("orderId") String orderId) {
        return orderService.pickUpTickets(orderId);
    }
}
