package com.elane.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 是spring 提供的安全配置 适配器
 * EnableWebSecurity 开启web安全
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    @Autowired
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //基于basic登录，任何访问都需要授权
//        httpserver.httpBasic().and().authorizeRequests().anyRequest().authenticated();
        //表单登录，登录页signIn.html，登录处理url /authentication/form
//        httpserver.formLogin()
//                .loginPage("/signIn.html")
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/signIn.html").permitAll()
//                .anyRequest().authenticated().and().csrf().disable();

        // 自定义登录接口
//        httpserver.formLogin()
//                .loginPage("/authencation/require")
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/authencation/require", "/signIn.html").permitAll()
//                .anyRequest().authenticated().and().csrf().disable();

        //自定义登录成功处理
        http.formLogin()
                //登录页面，未登录时，跳转地址
                .loginPage("/authencation/require")
                //登录处理接口
                .loginProcessingUrl("/authentication/form")
                .successHandler(loginAuthenticationSuccessHandler)
                .failureHandler(loginAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authencation/require", "/signIn.html", "/websocket.html", "/api/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }

    //注册加密bean
    @Bean
    PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
