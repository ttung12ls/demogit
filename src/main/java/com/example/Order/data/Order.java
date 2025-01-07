package com.example.Order.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("orders")
public class Order {
    @Id
    @Column("OrderID")
    private Integer orderId;
    
    @Column("Customer")
    private String customer;
    
    @Column("OrderDate")
    @Builder.Default
    private LocalDate orderDate = LocalDate.now();
    
    @Column("Freight")
    private BigDecimal freight;
    
    @Column("ShipCountry")
    private String shipCountry;
    
    @Column("ShippingCompany")
    private String shippingCompany;
}
