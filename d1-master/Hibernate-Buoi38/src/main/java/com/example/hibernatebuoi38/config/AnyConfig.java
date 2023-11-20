package com.example.hibernatebuoi38.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnyConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
