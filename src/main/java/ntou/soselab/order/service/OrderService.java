package ntou.soselab.order.service;

import lombok.extern.slf4j.Slf4j;
import ntou.soselab.order.client.TheaterClient;
import ntou.soselab.order.client.UserClient;
import ntou.soselab.order.client.dto.BookResultDTO;
import ntou.soselab.order.client.dto.BookShowDTO;
import ntou.soselab.order.client.dto.UserDTO;
import ntou.soselab.order.controller.dto.BookDTO;
import ntou.soselab.order.model.Order;
import ntou.soselab.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final TheaterClient theaterClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserClient userClient, TheaterClient theaterClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
        this.theaterClient = theaterClient;
    }

    public Order bookTickets(BookDTO bookDTO) {
        UserDTO userDTO = userClient.checkUser(bookDTO.getUserId());
        BookShowDTO bookShowDTO = BookShowDTO.builder()
                .showId(bookDTO.getShowId())
                .tickets(bookDTO.getTicket())
                .build();
        log.info("book show dto: {}", bookShowDTO);
        BookResultDTO bookResultDTO = theaterClient.bookShow(bookShowDTO);
        log.info("book result: {}", bookResultDTO);

        if (!bookResultDTO.isSuccess()) throw new BookNotSuccessfulException(bookResultDTO.getReason());

        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .isPickUp(false)
                .showId(bookDTO.getShowId())
                .ticket(bookDTO.getTicket())
                .timestamp(System.currentTimeMillis())
                .build();
        log.info("order result: {}", order);

        orderRepository.save(order);
        return order;
    }

    public Order pickUpTickets(String orderId) {
        Order order = orderRepository.findOne(orderId);
        boolean pickUp = order.isPickUp();

        if (pickUp) throw new AlreadyPickUpException();

        order.setPickUp(true);
        orderRepository.save(order);

        return order;
    }
}
