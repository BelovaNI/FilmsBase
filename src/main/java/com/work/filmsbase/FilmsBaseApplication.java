package com.work.filmsbase;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FilmsBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmsBaseApplication.class, args);
    }

}
