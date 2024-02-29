package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Lazy;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "translation_customer")
@Data
public class TranslationCustomer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    private String text;
    @OneToOne(mappedBy = "addressTranslation")
    private Customer customerByAddress;

    @OneToOne(mappedBy = "nameTranslation")
    private Customer customerByName;
}
