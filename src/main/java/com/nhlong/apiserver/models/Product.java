package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "product")
@Data

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(columnDefinition = "boolean NOT NULL default true")
    private Boolean status;

    @Column(name = "description_EN")
    private String descriptionEN;


    @OneToOne
    @JoinColumn(name = "name_translation_id")
    private TranslationProduct productNameTranslation;


    @OneToOne
    @JoinColumn(name = "description_translation_id")
    private TranslationProduct productDescTranslation;

    private double price;

    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant updatedDate;
}
