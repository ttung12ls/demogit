package com.example.Order.repository;

import com.example.Order.data.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
     Mono<Order> findByCustomer(String Customer);
     Mono<Order> findByOrderId(Long OrderId);
}
