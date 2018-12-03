package com.cg.capbook.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cg.capbook.services.UserServices;

@EnableWebSecurity
public class CapBookSecurity extends WebSecurityConfigurerAdapter {
private UserServices userServices;
private BCryptPasswordEncoder encoder;
private String signupurl="/userSignup";
public CapBookSecurity(UserServices userServices, BCryptPasswordEncoder encoder) {
	this.userServices = userServices;
	this.encoder = encoder;
}

@Override
protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, signupurl)
            .permitAll().anyRequest().authenticated();
}

}
