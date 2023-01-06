package com.epam.esm.controller.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@ComponentScan("com.epam.esm")
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
