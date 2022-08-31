package com.sycho.security.config;

import com.keencho.lib.spring.security.config.KcJwtAuthenticationFilter;
import com.keencho.lib.spring.security.config.KcWebSecurityExceptionHandling;
import com.keencho.lib.spring.security.provider.KcAuthenticationProviderManager;
import com.keencho.lib.spring.security.provider.KcJwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    KcAuthenticationProviderManager kcAuthenticationProviderManager;

    @Autowired
    KcJwtTokenProvider kcJwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // https://codehunter.cc/a/spring/spring-security-custom-authentication-authenticationprovider-vs-userdetailsservice
        // 일단 여기서는 authenticationProvider 방식을 사용하나 userDetailsService 방식을 사용할 수도 있음
        // 위의 링크의 두번째 답변이 도움이 될듯함.
        for (var provider : kcAuthenticationProviderManager.getProviders()) {
            http.authenticationProvider(provider);
//            http.userDetailsService(provider.getUserDetailsService());
        }

        // POST 요청 오픈
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.httpBasic().disable();

        // 에러 발생할 경우 미리 정의해둔 exception 메시지 & 코드 리턴
        http.exceptionHandling().authenticationEntryPoint(new KcWebSecurityExceptionHandling());

        // 세션 사용하지 않음
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(new KcJwtAuthenticationFilter(kcJwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        http
                .antMatcher("/**").authorizeRequests()
                //
                .antMatchers("/api/auth/test/admin").hasAuthority(AccountRoleCode.ROLE_ADMIN)
                //
                .antMatchers("/api/auth/test/user").hasAuthority(AccountRoleCode.ROLE_USER)
                //
                .antMatchers("/**").permitAll()
                //
                .anyRequest().authenticated();

        return http.build();
    }
}
