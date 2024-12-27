package com.example.Order.model;

import com.example.Order.data.Order;
import com.example.Order.data.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Long DetailId;
    private Long OrderId;
    private String Product;
    private BigDecimal Price;
    private Integer Quantity;
    private BigDecimal Sum;

    public static OrderDetail dtoToEntity (OrderDetailDTO orderDetailDTO){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(orderDetailDTO.getDetailId());
        orderDetail.setOrderId(orderDetailDTO.getOrderId());
        orderDetail.setProduct(orderDetailDTO.getProduct());
        orderDetail.setPrice(orderDetailDTO.getPrice());
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        orderDetail.setSum(orderDetailDTO.getSum());
        return orderDetail;
    }
    public static OrderDetailDTO entityToDto (OrderDetail orderDetail){
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setDetailId(orderDetail.getDetailId());
        orderDetailDTO.setOrderId(orderDetail.getOrderId());
        orderDetailDTO.setProduct(orderDetail.getProduct());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setSum(orderDetail.getSum());
        return orderDetailDTO;
    }

}