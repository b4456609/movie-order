package ntou.soselab.movie.repository;

import ntou.soselab.movie.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    public List<Order> findAllByUserId(String userId);
}
