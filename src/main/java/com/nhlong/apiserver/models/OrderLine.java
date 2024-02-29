package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "order_line")
@Data

public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "expected_delivery_date")
    private Date expectedDeliveryDate;

}
