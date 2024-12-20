package com.example.Order.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("orders")
public class Order {
    @Id
    @Column("order_id")
    private Long orderId;
    
    @Column("customer")
    private String customer;
    
    @Column("created_date")
    private LocalDateTime createdDate;
    
    @Column("status")
    private String status;
    
    @Column("freight")
    private BigDecimal freight;
    
    @Column("ship_country")
    private String shipCountry;
    
    @Column("shipping_company")
    private String shippingCompany;
}
