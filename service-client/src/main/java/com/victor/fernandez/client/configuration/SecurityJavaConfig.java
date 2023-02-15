package com.victor.fernandez.client.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN")
                .and().withUser("client").password(encoder().encode("client")).roles("CLIENT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/v1/client/**")
                .hasRole("CLIENT")
                .antMatchers(HttpMethod.GET,"/v1/client/detail/**")
                .hasRole("CLIENT")
                .antMatchers(HttpMethod.PUT,"/v1/client/**")
                .hasRole("CLIENT")
                .antMatchers(HttpMethod.DELETE,"/v1/client/**")
                .hasRole("CLIENT")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
