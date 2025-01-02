package com.example.Order.model;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import com.example.Order.data.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private String customer;
    private LocalDate orderDate = LocalDate.now();
    private BigDecimal freight;
    private String shipCountry;
    private String shippingCompany;

    public static Order dtoToEntity (OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomer(orderDTO.getCustomer());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setFreight(orderDTO.getFreight());
        order.setShipCountry(orderDTO.getShipCountry());
        order.setShippingCompany(orderDTO.getShippingCompany());
        return order;
    }
    public static OrderDTO entityToDto (Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setFreight(order.getFreight());
        orderDTO.setShipCountry(order.getShipCountry());
        orderDTO.setShippingCompany(order.getShippingCompany());
        return orderDTO;
    }

}
