package com.sycho.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "ADMIN_ACCOUNT")
public class AdminAccount extends AccountBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
