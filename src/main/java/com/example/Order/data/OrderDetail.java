package com.example.Order.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("orderdetails")
public class OrderDetail {
    @Id
    @Column("DetailID")
    private Long DetailId;
    @Column ("OrderID")
    private Long OrderId;
    @Column ("Product")
    private String Product;
    @Column ("Price")
    private BigDecimal Price;
    @Column ("Quantity")
    private Integer Quantity;
    @Column ("Sum")
    private BigDecimal Sum;
    @Transient
    private Order order;

}
