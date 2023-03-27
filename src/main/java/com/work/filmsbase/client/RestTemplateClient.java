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
    RestTemplate restTemplate;
    ConfigProperties configProperties;

    @Autowired
    public RestTemplateClient(RestTemplate restTemplate, ConfigProperties configProperties) {
        this.restTemplate = restTemplate;
        this.configProperties = configProperties;
    }
    public String getComponentsBuilder(FilmFilterDTO filmFilterDTO) {
        String componentsBuilder = UriComponentsBuilder.fromPath(configProperties.getUrl())
                .queryParam("order", filmFilterDTO.getOrder())
                .queryParam("type", filmFilterDTO.getType())
                .queryParam("keyword", filmFilterDTO.getKeyword())
                .build().toString();
        return componentsBuilder;
    }
    public  String getKey() {
        return "X-API-KEY=" + configProperties.getKey();
    }
    public ResponseEntity<String> executeRestCall(String url, HttpMethod get, HttpEntity<String> entity, Class<String> stringClass, Map<String, String> params) {
        ResponseEntity<String> film = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, params);
        return film;
    }
}
