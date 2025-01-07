package com.example.Order.controller;

import com.example.Order.model.OrderDTO;
import com.example.Order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Flux<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Check if customer order exists")
    @GetMapping(value = "/checkDuplicate/{customer}")
    public ResponseEntity<Mono<Boolean>> checkDuplicate(
            @Parameter(description = "Customer name to check")
            @PathVariable @NotBlank String customer) {
        return ResponseEntity.ok(orderService.checkDuplicate(customer));
    }

    @Operation(summary = "Find order by ID")
    @GetMapping("/{orderId}")
    public ResponseEntity<Mono<OrderDTO>> findById(
            @Parameter(description = "Order ID")
            @PathVariable @Min(1) Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    @Operation(summary = "Find orders by customer name")
    @GetMapping("/customer/{customer}")
    public ResponseEntity<Flux<OrderDTO>> findByCustomerName(
            @Parameter(description = "Customer name")
            @PathVariable @NotBlank String customer) {
        return ResponseEntity.ok(orderService.findByCustomerName(customer));
    }

    @Operation(summary = "Create a new order with details")
    @PostMapping
    public ResponseEntity<Mono<OrderDTO>> createOrder(
            @Parameter(description = "Order details including order items")
            @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderDTO));
    }

    @Operation(summary = "Update an existing order and its details")
    @PutMapping("/{orderId}")
    public ResponseEntity<Mono<OrderDTO>> updateOrder(
            @Parameter(description = "Order ID to update")
            @PathVariable @Min(1) Integer orderId,
            @Parameter(description = "Updated order details including order items")
            @Valid @RequestBody OrderDTO orderDTO) {
        if (orderDTO.getOrderId() != null && !orderId.equals(orderDTO.getOrderId())) {
            return ResponseEntity.badRequest().build();
        }
        orderDTO.setOrderId(orderId); // Ensure using path variable ID
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDTO));
    }

    @Operation(summary = "Delete an order and its details")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Mono<Void>> deleteOrder(
            @Parameter(description = "Order ID to delete")
            @PathVariable @Min(1) Integer orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
