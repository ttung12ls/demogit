package com.example.Order.repository;

import com.example.Order.data.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
    @Query("SELECT * FROM orders WHERE LOWER(Customer) LIKE LOWER(CONCAT('%', :customerName, '%'))")
    Flux<Order> findByCustomer(String customerName);
}
