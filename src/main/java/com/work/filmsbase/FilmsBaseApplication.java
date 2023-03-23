package com.work.filmsbase;

import com.work.filmsbase.configuration.ConfigProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class FilmsBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmsBaseApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper(){ return new ModelMapper(); }
    @Bean
    ConfigProperties configProperties() {return new ConfigProperties();}
}
