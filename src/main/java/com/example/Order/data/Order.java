package com.example.Order.data;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("orders")
public class Order {
    @Id
    @Column ("OrderID")
    private Long orderId;
    @Column ("Customer")
    private String customer;
    @Column ("OrderDate")
    private LocalDate orderDate;
    @Column ("Freight")
    private BigDecimal freight;
    @Column ("ShipCountry")
    private String shipCountry;
    @Column ("ShippingCompany")
    private String shippingCompany;

}
