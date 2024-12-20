package com.example.Order.controller;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping
    public ResponseEntity<Flux<OrderDTO>>
    getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping(value = "/checkDuplicate/{Customer}")
    public ResponseEntity<Mono<Boolean>> checkDuplicate(@PathVariable String Customer) {
     return ResponseEntity.ok(orderService.checkDuplicate(Customer));
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<Mono<OrderDTO>> findById(@PathVariable Long OrderId) {
        return ResponseEntity.ok(orderService.findById(OrderId));
    }

    @GetMapping("/customer/{Customer}")
    public ResponseEntity<Mono<OrderDTO>> findByCustomerName(@PathVariable String Customer) {
        return ResponseEntity.ok(orderService.findByCustomerName(Customer));

    }
    @PostMapping
    public ResponseEntity<Mono<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDTO));
    }
    @PutMapping("/{OrderId}")
    public Mono<Order> updateOrder(@PathVariable Long OrderId, @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(OrderId, orderDTO);
    }
    @DeleteMapping("/{OrderId}")
    public Mono<Void> deleteOrder(@PathVariable Long OrderId) {
        return orderService.deleteOrder(OrderId);
    }

}
