package com.sycho.security.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@MappedSuperclass
public class AccountBaseModel {

    protected LocalDateTime dtCreatedAt = LocalDateTime.now();

    protected LocalDateTime dtUpdatedAt = LocalDateTime.now();

    protected LocalDateTime dtLastLoggedInAt;

    protected int loginAttemptCnt = 0;

    protected boolean locked = false;

    protected String loginId;

    protected String loginPw;
}
