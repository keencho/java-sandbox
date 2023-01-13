package com.keencho.hibernate6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "color_one")
public class ColorOne {

    @Id
    @GeneratedValue
    UUID id;

    String name;

    public ColorOne(String name) {
        this.name = name;
    }
}
