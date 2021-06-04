package com.ddwj.kimgram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean // 비밀번호 해쉬를 하기 위한 코드
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**","/api/**").authenticated()  // 인증이 필요한 주소들이다.
                .anyRequest().permitAll()  // 이 외의 모든 코드는 접근을 허용한다.

                .and()

                .formLogin() // anyMatchers 로 오는 경우 formLogin을 할 것이다.
                .loginPage("/auth/signin")// GET 방식이다. formLogin 으로 온다면 "/auth/signin" 으로 간다.
                .loginProcessingUrl("/auth/signin")// POST 방식이다. -> 스프링 시큐리티가 로그인을 처리해준다.
                .defaultSuccessUrl("/"); // 로그인 성공하면 "/"로 간다.
    }

}
