package com.sycho.security.core.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService<ACCOUNT, USER, LOGIN_DATA> {

    LOGIN_DATA login(HttpServletRequest request, HttpServletResponse response, UsernamePasswordAuthenticationToken token, boolean rememberMe);

    LOGIN_DATA reload();
}