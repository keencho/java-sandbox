package com.sycho.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // POST 요청 오픈
        http.csrf().disable();

        // 에러 발생할 경우 미리 정의해둔 exception 메시지 & 코드 리턴
        http.exceptionHandling().authenticationEntryPoint(new WebSecurityExceptionHandling());

        // 세션 사용하지 않음
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .antMatcher("/**").authorizeRequests()
                //
                .antMatchers("/web/login", "/api/login").permitAll()
                //
                .anyRequest().authenticated();

        return http.build();
    }
}
