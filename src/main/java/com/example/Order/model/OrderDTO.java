package com.example.Order.model;

import com.example.Order.data.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long OrderId;
    private String Customer;
    private LocalDate OrderDate;
    private BigDecimal Freight;
    private String ShipCountry;
    private String ShippingCompany;

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
