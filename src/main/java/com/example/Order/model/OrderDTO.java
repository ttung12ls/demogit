package com.example.Order.model;

import com.example.Order.data.Order;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    
    @NotBlank(message = "Customer name is required")
    private String customer;
    
    @NotNull(message = "Created date is required")
    private LocalDateTime createdDate;
    
    @NotBlank(message = "Status is required")
    private String status;
    
    private BigDecimal freight;
    private String shipCountry;
    private String shippingCompany;

    public static Order dtoToEntity (OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomer(orderDTO.getCustomer());
        order.setFreight(orderDTO.getFreight());
        order.setShipCountry(orderDTO.getShipCountry());
        order.setShippingCompany(orderDTO.getShippingCompany());
        return order;
    }
    public static OrderDTO entityToDto (Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setFreight(order.getFreight());
        orderDTO.setShipCountry(order.getShipCountry());
        orderDTO.setShippingCompany(order.getShippingCompany());
        return orderDTO;
    }

}
