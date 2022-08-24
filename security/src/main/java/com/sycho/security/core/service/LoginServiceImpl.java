package com.sycho.security.core.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class LoginServiceImpl<LOGIN_DATA> implements LoginService<LOGIN_DATA> {

    @Override
    public LOGIN_DATA login(HttpServletRequest request, HttpServletResponse response, UsernamePasswordAuthenticationToken token, boolean rememberMe) {
        return null;
    }

    @Override
    public LOGIN_DATA reload() {
        return null;
    }
}
