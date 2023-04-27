package com.work.filmsbase;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.configuration.ConfigProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class FilmsBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmsBaseApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){ return new ModelMapper(); }
    @Bean
    ConfigProperties configProperties() {return new ConfigProperties();}
    @Bean
    FilmFilterDTO filmFilterDTO(){return new FilmFilterDTO();}
}
