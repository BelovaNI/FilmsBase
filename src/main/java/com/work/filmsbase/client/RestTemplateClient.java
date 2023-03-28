package com.work.filmsbase.client;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.configuration.ConfigProperties;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@NoArgsConstructor
@Component
public class RestTemplateClient {
    ConfigProperties configProperties;

    @Autowired
    public RestTemplateClient(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }
    public String getURI(FilmFilterDTO filmFilterDTO) {
        String componentsBuilder = UriComponentsBuilder.fromPath(configProperties.getUrl())
                .queryParam("order", filmFilterDTO.getOrder())
                .queryParam("type", filmFilterDTO.getType())
                .queryParam("keyword", filmFilterDTO.getKeyword())
                .build().toString();
        return componentsBuilder;
    }
    public  String getKey() {
        return configProperties.getKey();
    }
}

