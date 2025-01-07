package com.example.Order.service;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.repository.OrderRepository;
import com.ttung.commonservice.common.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.Order.model.OrderDTO.dtoToEntity;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Flux<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .map(order -> OrderDTO.entityToDto(order))
                .switchIfEmpty(Mono.error(new CommonException("OE03", "List empty", HttpStatus.NOT_FOUND)));
    }

    public Mono<OrderDTO> findById(Integer OrderId) {
        return orderRepository.findById(OrderId)
                .map(OrderDTO::entityToDto)  // Chuyển đổi từ entity sang DTO
                .switchIfEmpty(Mono.error(new CommonException("OE01", "Not found", HttpStatus.NOT_FOUND)));
    }

    public Flux<OrderDTO> findByCustomerName(String customer) {
        if (customer == null || customer.trim().isEmpty()) {
            return Flux.error(new CommonException("OE04", 
                "Customer name cannot be empty", 
                HttpStatus.BAD_REQUEST));
        }
        return orderRepository.findByCustomer(customer.trim())
                .map(OrderDTO::entityToDto)
                .switchIfEmpty(Flux.error(new CommonException("OE01", 
                    String.format("No orders found for customer containing: '%s'", customer), 
                    HttpStatus.NOT_FOUND)));
    }

    public Mono<Boolean> checkDuplicate(String customer) {
        return orderRepository.findByCustomer(customer)
                .hasElements();
    }

    public Mono<OrderDTO> createOrder(OrderDTO orderDTO) {
        return checkDuplicate(orderDTO.getCustomer())
                .flatMap(aBoolean -> {
                    if (Boolean.TRUE.equals(aBoolean)) {
                        return Mono.error(new CommonException("OE02", "User existed", HttpStatus.BAD_REQUEST));
                    } else {
                        Order order = Order.builder()
                                .customer(orderDTO.getCustomer())
                                .orderDate(orderDTO.getOrderDate())
                                .freight(orderDTO.getFreight())
                                .shipCountry(orderDTO.getShipCountry())
                                .shippingCompany(orderDTO.getShippingCompany())
                                .build();
                        return orderRepository.save(order)
                                .map(OrderDTO::entityToDto);
                    }
                });
    }

    public Mono<OrderDTO> updateOrder(Integer orderId, OrderDTO orderDTO) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new CommonException("OE01", "Not found", HttpStatus.NOT_FOUND)))
                .flatMap(existingOrder -> {
                    Order updatedOrder = Order.builder()
                            .orderId(existingOrder.getOrderId())
                            .customer(orderDTO.getCustomer())
                            .orderDate(orderDTO.getOrderDate())
                            .freight(orderDTO.getFreight())
                            .shipCountry(orderDTO.getShipCountry())
                            .shippingCompany(orderDTO.getShippingCompany())
                            .build();
                    return orderRepository.save(updatedOrder);
                })
                .map(OrderDTO::entityToDto)
                .doOnSuccess(updatedOrder -> log.info("Order updated successfully: {}", updatedOrder))
                .doOnError(error -> log.error("Failed to update order: {}", error.getMessage(), error));
    }

    public Mono<Void> deleteOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new CommonException("OE01", "Not found", HttpStatus.NOT_FOUND)))
                .flatMap(existingOrder -> orderRepository.deleteById(orderId))
                .doOnSuccess(v -> log.info("Order deleted successfully for ID: {}", orderId))
                .doOnError(error -> log.error("Failed to delete order: {}", error.getMessage(), error));
    }

}
