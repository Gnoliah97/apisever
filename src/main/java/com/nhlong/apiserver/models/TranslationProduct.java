package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "translation_product")
@Data
public class TranslationProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    private String text;
    @OneToOne(mappedBy = "productDescTranslation")
    private Product productDescrTranslation;

    @OneToOne(mappedBy = "productNameTranslation")
    private Product productNameTranslation;


}
