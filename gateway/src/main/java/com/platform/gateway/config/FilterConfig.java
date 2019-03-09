package com.platform.gateway.config;

import com.platform.gateway.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public LoginFilter tokenFilter(){
        return new LoginFilter();
    }

}
