package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.UserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index", "/css/*", "/js?*").permitAll()
//                .antMatchers("/api/**").hasRole(GUEST.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails davUser =   User.builder()
                .username("david")
                .password(passwordEncoder.encode("password1"))
                .authorities(GUEST.getGrantedAuthority())
                .build();


        UserDetails samUser = User.builder()
                .username("ochia")
                .password(passwordEncoder.encode("password2"))
                .authorities(HOST.getGrantedAuthority())
                .build();




        UserDetails tomUser = User.builder()
                .username("tomas")
                .password(passwordEncoder.encode("password3"))
                .authorities(ADMIN.getGrantedAuthority())
                .build();




        return new InMemoryUserDetailsManager(
                davUser,
                samUser,
                tomUser
        );
    }
}
