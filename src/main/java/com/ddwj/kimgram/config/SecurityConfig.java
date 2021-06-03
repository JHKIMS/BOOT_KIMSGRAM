package com.ddwj.kimgram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**").authenticated()  // 인증이 필요한 주소들이다.
                .anyRequest().permitAll()  // 이 외의 모든 코드는 접근을 허용한다.

                .and()

                .formLogin() // anyMatchers 로 오는 경우 formLogin을 할 것이다.
                .loginPage("/auth/signin")// formLogin 으로 온다면 "/auth/signin" 으로 간다.
                .defaultSuccessUrl("/"); // 로그인 성공하면 "/"로 간다.
    }

}
