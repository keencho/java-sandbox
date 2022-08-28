package com.sycho.security.core.service;

import com.sycho.security.core.manager.AuthenticationProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;

public abstract class LoginServiceImpl<ACCOUNT, USER, LOGIN_DATA> implements LoginService<ACCOUNT, USER, LOGIN_DATA> {

    protected abstract Type getAccountType();

    @Autowired
    AuthenticationProviderManager authenticationProviderManager;

    @Autowired
    DefaultUserDetailsService<ACCOUNT, USER> defaultUserDetailsService;

    @Override
    public LOGIN_DATA login(HttpServletRequest request, HttpServletResponse response, UsernamePasswordAuthenticationToken token, boolean rememberMe) {
        Authentication authentication;

        try {
            var authenticationProvider = authenticationProviderManager.getAuthenticationProvider(this.getAccountType());
            authentication = authenticationProvider.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            // TODO: 로그인 횟수 검증 / db 업데이트
        } catch (LockedException ex) {

        } catch (DisabledException ex) {

        } catch (Exception ex) {

        }


        return null;
    }

    @Override
    public LOGIN_DATA reload() {
        var currentAuth = SecurityContextHolder.getContext().getAuthentication();

        var newAuth = new UsernamePasswordAuthenticationToken(defaultUserDetailsService.loadUserByUsername(currentAuth.getName()), null, currentAuth.getAuthorities());

        var securityUser = newAuth.getPrincipal();

        return null;
    }
}
