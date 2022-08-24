package com.sycho.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends AccountBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
