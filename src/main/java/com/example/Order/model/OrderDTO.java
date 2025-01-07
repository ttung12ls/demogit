package com.example.Order.model;

import com.example.Order.data.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer orderId;
    
    @NotBlank(message = "Customer name is required")
    private String customer;
    
    @NotNull(message = "Order date is required")
    private LocalDate orderDate = LocalDate.now();
    
    @PositiveOrZero(message = "Freight must be zero or positive")
    private BigDecimal freight;
    
    @NotBlank(message = "Ship country is required")
    private String shipCountry;
    
    @NotBlank(message = "Shipping company is required")
    private String shippingCompany;

    public static Order dtoToEntity(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .customer(orderDTO.getCustomer())
                .orderDate(orderDTO.getOrderDate())
                .freight(orderDTO.getFreight())
                .shipCountry(orderDTO.getShipCountry())
                .shippingCompany(orderDTO.getShippingCompany())
                .build();
    }

    public static OrderDTO entityToDto(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .customer(order.getCustomer())
                .orderDate(order.getOrderDate())
                .freight(order.getFreight())
                .shipCountry(order.getShipCountry())
                .shippingCompany(order.getShippingCompany())
                .build();
    }
}
