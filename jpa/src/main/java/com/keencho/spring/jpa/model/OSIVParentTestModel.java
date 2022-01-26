package com.keencho.spring.jpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "osiv_parent_test_model")
public class OSIVParentTestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String keyword;

    public OSIVParentTestModel(String keyword) {
        this.keyword = keyword;
    }
}
