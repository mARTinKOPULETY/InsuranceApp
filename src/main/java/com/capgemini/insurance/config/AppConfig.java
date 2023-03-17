package com.capgemini.insurance.config;

import org.modelmapper.*;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}