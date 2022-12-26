package com.keencho.jpa31.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private int pages;

    // 판매율 (소수점)
    private BigDecimal sellingRates;
    // 분류기호 (소수점)
    private BigDecimal classificationSymbol;

    // 발매일
    private LocalDateTime releaseDateTime;
}
