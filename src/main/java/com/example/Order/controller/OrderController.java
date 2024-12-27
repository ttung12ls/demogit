package com.example.Order.controller;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.service.OrderService;
import com.example.Order.utils.Constant;
import com.google.gson.Gson;
import com.ttung.commonservice.utils.CommonFunction;
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
public class OrderController {
    @Autowired
    OrderService orderService;

    Gson gson = new Gson();

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
    public ResponseEntity<Mono<OrderDTO>> createOrder(@RequestBody String requestStr) {
        InputStream inputStream = OrderController.class.getClassLoader().getResourceAsStream(Constant.JSON_REQ_CREATE_ORDER);
        CommonFunction.jsonValidate(inputStream,requestStr);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(gson.fromJson(requestStr,OrderDTO.class)));
    }
    @PutMapping
    public Mono<Order> updateOrder( @RequestBody OrderDTO orderDTO) {
        return orderService.updateOrder(orderDTO.getOrderId() , orderDTO);
    }
    @DeleteMapping("/{OrderId}")
    public Mono<Void> deleteOrder(@PathVariable Long OrderId) {
        return orderService.deleteOrder(OrderId);
    }

}
