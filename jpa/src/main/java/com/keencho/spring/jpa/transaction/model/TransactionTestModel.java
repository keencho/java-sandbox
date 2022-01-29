package com.keencho.spring.jpa.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_test_model")
public class TransactionTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String payKey;

    int amount;

}
