package com.example.Order.service;

import com.example.Order.data.Order;
import com.example.Order.model.OrderDTO;
import com.example.Order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public Flux<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .map(this::convertToDTO);
    }

    public Mono<Boolean> checkDuplicate(String customer) {
        return orderRepository.existsByCustomer(customer);
    }

    public Mono<OrderDTO> findById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertToDTO);
    }

    public Mono<OrderDTO> findByCustomerName(String customer) {
        return orderRepository.findByCustomer(customer)
                .map(this::convertToDTO);
    }

    public Mono<OrderDTO> createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        return orderRepository.save(order)
                .map(this::convertToDTO);
    }

    public Mono<Order> updateOrder(Long orderId, OrderDTO orderDTO) {
        return orderRepository.findById(orderId)
                .flatMap(existingOrder -> {
                    existingOrder.setCustomer(orderDTO.getCustomer());
                    existingOrder.setCreatedDate(orderDTO.getCreatedDate());
                    existingOrder.setStatus(orderDTO.getStatus());
                    existingOrder.setFreight(orderDTO.getFreight());
                    existingOrder.setShipCountry(orderDTO.getShipCountry());
                    existingOrder.setShippingCompany(orderDTO.getShippingCompany());
                    return orderRepository.save(existingOrder);
                });
    }

    public Mono<Void> deleteOrder(Long orderId) {
        return orderRepository.deleteById(orderId);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomer(order.getCustomer());
        dto.setCreatedDate(order.getCreatedDate());
        dto.setStatus(order.getStatus());
        dto.setFreight(order.getFreight());
        dto.setShipCountry(order.getShipCountry());
        dto.setShippingCompany(order.getShippingCompany());
        return dto;
    }

    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setCustomer(dto.getCustomer());
        order.setCreatedDate(dto.getCreatedDate());
        order.setStatus(dto.getStatus());
        order.setFreight(dto.getFreight());
        order.setShipCountry(dto.getShipCountry());
        order.setShippingCompany(dto.getShippingCompany());
        return order;
    }
}
