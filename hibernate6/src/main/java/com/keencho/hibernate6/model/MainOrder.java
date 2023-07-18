package com.keencho.hibernate6.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.PartitionKey;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "main_order")
public class MainOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @PartitionKey
    private LocalDateTime createdDateTime;

    @Embedded
    private Address fromAddress;

    @Embedded
    private Address toAddress;
}
