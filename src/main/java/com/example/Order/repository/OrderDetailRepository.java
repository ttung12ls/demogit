package com.example.Order.repository;

import com.example.Order.data.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrderDetailRepository extends ReactiveCrudRepository<Order, Long> {
}

