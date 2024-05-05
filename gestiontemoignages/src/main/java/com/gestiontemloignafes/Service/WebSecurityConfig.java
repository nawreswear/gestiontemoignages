package com.gestiontemloignafes.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.cors()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/commentaire/**","/commentaire/article/{idArticle}/**","/commentaire/article/{idArticle}/**")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}

