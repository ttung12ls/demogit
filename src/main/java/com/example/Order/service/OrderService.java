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
    OrderRepository orderRepository;
    public Flux<OrderDTO> getAllOrders() {
      return orderRepository.findAll()
              .map(order -> OrderDTO.entityToDto(order))
              .switchIfEmpty(Mono.error(new Exception("Order list is empy")));
}
    public Mono<OrderDTO> findById(Long OrderId) {
        return orderRepository.findById(OrderId)
                .map(OrderDTO::entityToDto)  // Chuyển đổi từ entity sang DTO
                .switchIfEmpty(Mono.error(new Exception("Order not found for ID: " + OrderId)));
    }
    public Mono<OrderDTO> findByCustomerName(String Customer) {
        return orderRepository.findByCustomer(Customer)
                .map(OrderDTO::entityToDto)  // Chuyển đổi từ entity sang DTO
                .switchIfEmpty(Mono.error(new Exception("No orders found for customer: " + Customer)));
    }
public Mono<Boolean> checkDuplicate(String Customer) {
        return orderRepository.findByCustomer(Customer)
                .flatMap(order -> Mono.just(true))
                .switchIfEmpty(Mono.just(false));
}

    public Mono<OrderDTO> createOrder(OrderDTO orderDTO) {
        return checkDuplicate(orderDTO.getCustomer())
                .flatMap(aBoolean -> {
                    if(Boolean.TRUE.equals(aBoolean)) {
                        return Mono.error(new Exception("Customer already exists"));
                    }else {
                        return Mono.just(orderDTO)
                                .map(OrderDTO::dtoToEntity)
                                .flatMap(order -> orderRepository.save(order))
                                .map(OrderDTO::entityToDto)
                                .doOnError(throwable -> log.error(throwable.getMessage()))
                                .doOnSuccess(orderDTO1 -> {
                                });

                    }
                });
    }
    public Mono<Order> updateOrder(Long orderId, OrderDTO orderDTO) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new Exception("Order not found for ID: " + orderId)))
                .flatMap(existingOrder -> {
                    existingOrder.setCustomer(orderDTO.getCustomer());
                    existingOrder.setOrderDate(orderDTO.getOrderDate());
                    existingOrder.setFreight(orderDTO.getFreight());
                    existingOrder.setShipCountry(orderDTO.getShipCountry());
                    existingOrder.setShippingCompany(orderDTO.getShippingCompany());
                    return orderRepository.save(existingOrder);
                })
                .doOnSuccess(updatedOrder -> log.info("Order updated successfully: {}", updatedOrder))
                .doOnError(error -> log.error("Failed to update order: {}", error.getMessage(), error));
    }
    public Mono<Void> deleteOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new Exception("Order not found for ID: " + orderId)))
                .flatMap(existingOrder -> orderRepository.deleteById(orderId))
                .doOnSuccess(v -> log.info("Order deleted successfully for ID: {}", orderId))
                .doOnError(error -> log.error("Failed to delete order: {}", error.getMessage(), error));
    }

}
