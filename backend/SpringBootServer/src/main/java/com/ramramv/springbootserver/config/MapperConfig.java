package com.ramramv.springbootserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        // ModelMapper modelMapper = new ModelMapper();
        // //strict
        // modelMapper.getConfiguration().setAmbiguityIgnored(false);
        return new ModelMapper();
    }
}
