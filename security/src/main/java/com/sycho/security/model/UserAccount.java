package com.sycho.security.model;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends KcAccountBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public int maxLoginAttemptCnt() {
        return 5;
    }

    @Override
    public Collection<? extends GrantedAuthority> authorities() {
        return null;
    }
}
