package com.example.Order.controller;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.service.OrderService;
import com.example.Order.utils.Constant;
import com.google.gson.Gson;
import com.ttung.commonservice.utils.CommonFunction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order Controller", description = "Order management APIs")
public class OrderController {

    @Autowired
    private OrderService orderService;
    Gson gson = new Gson();
    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<Flux<OrderDTO>>
    getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @Operation(summary = "Check if customer order exists")
    @GetMapping(value = "/checkDuplicate/{Customer}")
    public ResponseEntity<Mono<Boolean>> checkDuplicate(
            @Parameter(description = "Customer name to check")
            @PathVariable String Customer) {
     return ResponseEntity.ok(orderService.checkDuplicate(Customer));
    }
    @Operation(summary = "Find order by ID")
    @GetMapping("/{OrderId}")
    public ResponseEntity<Mono<OrderDTO>> findById(
            @Parameter(description = "Order ID")
            @PathVariable Long OrderId) {
        return ResponseEntity.ok(orderService.findById(OrderId));
    }
    @Operation(summary = "Find order by customer name")
    @GetMapping("/customer/{Customer}")
    public ResponseEntity<Mono<OrderDTO>> findByCustomerName(
            @Parameter(description = "Customer name")
            @PathVariable String Customer) {
        return ResponseEntity.ok(orderService.findByCustomerName(Customer));

    }
    @Operation(summary = "Create a new order")
    @PostMapping
    public ResponseEntity<Mono<OrderDTO>> createOrder(
            @Parameter(description = "Order details")
            @Valid @RequestBody String requestStr) {
        InputStream inputStream = OrderController.class.getClassLoader().getResourceAsStream(Constant.JSON_REQ_CREATE_ORDER);
        CommonFunction.jsonValidate(inputStream,requestStr);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(gson.fromJson(requestStr,OrderDTO.class)));
    }
    @Operation(summary = "Update an existing order")
    @PutMapping
    public Mono<Order> updateOrder(
            @Parameter(description = "Updated order details")
            @Valid @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO.getOrderId() , orderDTO);
    }
    @Operation(summary = "Delete an order")
    @DeleteMapping("/{OrderId}")
    public Mono<Void> deleteOrder(
            @Parameter(description = "Order ID to delete")
            @PathVariable Long OrderId) {
        return orderService.deleteOrder(OrderId);
    }

}
