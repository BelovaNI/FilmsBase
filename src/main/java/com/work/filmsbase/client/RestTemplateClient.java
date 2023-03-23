package com.work.filmsbase.client;
import com.work.filmsbase.configuration.ConfigProperties;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
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
    public ResponseEntity<String> getAllFilmsByFilterFromKinopoisk() {   // исправить название, убрать урл, токен
        String URL = configProperties.getUrl();
        String token = configProperties.getKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> film = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class, 1);
        return film;
    }
}
