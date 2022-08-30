package com.sycho.security.model;

import com.keencho.lib.spring.security.model.KcAccountBaseModel;
import com.sycho.security.config.AccountRoleCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends KcAccountBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //////////////////////////////////////////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> authorities() {
        var set = new HashSet<String>();
        set.add(AccountRoleCode.ROLE_COMMON);
        set.add(AccountRoleCode.ROLE_USER);

        return set.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public int maxLoginAttemptCount() {
        return 5;
    }
}
