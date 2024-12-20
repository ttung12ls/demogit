package com.example.Order.controller;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order Controller", description = "Order management APIs")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Get all orders")
    @GetMapping
    public Flux<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(summary = "Check if customer order exists")
    @GetMapping("/checkDuplicate/{customer}")
    public Mono<Boolean> checkDuplicate(
            @Parameter(description = "Customer name to check") 
            @PathVariable String customer) {
        return orderService.checkDuplicate(customer);
    }

    @Operation(summary = "Find order by ID")
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OrderDTO> findById(
            @Parameter(description = "Order ID") 
            @PathVariable Long orderId) {
        return orderService.findById(orderId);
    }

    @Operation(summary = "Find order by customer name")
    @GetMapping("/customer/{customer}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<OrderDTO> findByCustomerName(
            @Parameter(description = "Customer name") 
            @PathVariable String customer) {
        return orderService.findByCustomerName(customer);
    }

    @Operation(summary = "Create a new order")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<OrderDTO> createOrder(
            @Parameter(description = "Order details") 
            @Valid @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @Operation(summary = "Update an existing order")
    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Order> updateOrder(
            @Parameter(description = "Order ID to update") 
            @PathVariable Long orderId,
            @Parameter(description = "Updated order details") 
            @Valid @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderId, orderDTO);
    }

    @Operation(summary = "Delete an order")
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteOrder(
            @Parameter(description = "Order ID to delete") 
            @PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }
}
