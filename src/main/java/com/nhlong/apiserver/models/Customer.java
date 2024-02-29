package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Table(name = "customer")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 4, message = "Name must be at least 4 characters long")
    @Pattern(regexp = "^[A-Z].*", message = "Name must start with an uppercase letter")
    @Column(name = "name_EN")
    private String nameEN;

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(columnDefinition = "boolean NOT NULL default true")
    private Boolean status;

    @NotBlank(message = "Address is required")
    @Size(min = 7)
    @Column(name = "address_EN")
    private String addressEN;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Contact number must start with 0 and contain 10 digits")
    @Column(name = "contact_number")
    private String contactNumber;

    @OneToOne
    @JoinColumn(name = "name_translation_id")
    private TranslationCustomer nameTranslation;

    @OneToOne
    @JoinColumn(name = "address_translation_id")
    private TranslationCustomer addressTranslation;

    @OneToMany(mappedBy = "customer")
    private List<OrderLine> orderLines;
    @CreationTimestamp
    private Instant createdDate;
    @UpdateTimestamp
    private Instant updatedDate;

}
