package com.nhlong.apiserver.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "language")
@Data

public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

}
