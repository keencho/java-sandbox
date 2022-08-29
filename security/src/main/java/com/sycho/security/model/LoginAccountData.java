package com.sycho.security.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class LoginAccountData {
    private String loginId;
    private LoginAccountType loginAccountType;
    private Set<String> authorities;
}
