package com.example.Order.repository;

import com.example.Order.data.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    Mono<Order> findByCustomer(String customer);
    Mono<Boolean> existsByCustomer(String customer);
}
