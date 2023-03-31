package com.work.filmsbase.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {
    ConfigProperties configProperties;
    @Autowired
    public RestTemplateConfig(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }
    @Bean
    public RestTemplate restTemplate(){
//        return new RestTemplateBuilder().rootUri(configProperties.getUrl()).build();
        return new RestTemplate();
    }
}
