package com.keencho.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;
}
