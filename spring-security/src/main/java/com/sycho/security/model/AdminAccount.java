package com.sycho.security.model;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "ADMIN_ACCOUNT")
public class AdminAccount extends KcAccountBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
