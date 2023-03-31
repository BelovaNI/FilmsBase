package com.work.filmsbase.client;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.DTO.FilmGetResponseDTO;
import com.work.filmsbase.configuration.ConfigProperties;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class RestTemplateClient {
    ConfigProperties configProperties;
    RestTemplate restTemplate;
    FilmMapper filmMapper;

    @Autowired
    public RestTemplateClient(ConfigProperties configProperties, RestTemplate restTemplate, FilmMapper filmMapper) {
        this.configProperties = configProperties;
        this.restTemplate = restTemplate;
        this.filmMapper = filmMapper;
    }
    public String getURI(FilmFilterDTO filmFilterDTO) {
        String componentsBuilder = UriComponentsBuilder.fromUriString(configProperties.getUrl())
                .queryParam("yearFrom", filmFilterDTO.getYearFrom())
                .queryParam("yearTo", filmFilterDTO.getYearTo())
                .queryParam("type", filmFilterDTO.getType())
                .queryParam("keyword", filmFilterDTO.getKeyword())
                .queryParam("genres", filmFilterDTO.getGenres())
                .build().toUriString();
        return componentsBuilder;
    }
    public ResponseEntity<FilmGetResponseDTO> getAllFilmsByFilterFromKinopoisk(FilmFilterDTO filmFilterDTO){
        String token = configProperties.getKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<FilmGetResponseDTO> film = restTemplate.exchange(getURI(filmFilterDTO), HttpMethod.GET, entity, FilmGetResponseDTO.class);
        return film;
    }
}

