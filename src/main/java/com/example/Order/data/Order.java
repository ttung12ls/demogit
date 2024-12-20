package com.example.Order.data;

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
    private Long OrderId;
    @Column ("Customer")
    private String Customer;
    @Column ("OrderDate")
    private LocalDate OrderDate;
    @Column ("Freight")
    private BigDecimal Freight;
    @Column ("ShipCountry")
    private String ShipCountry;
    @Column ("ShippingCompany")
    private String ShippingCompany;

}
