package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class FilmServiceImpl {
    FilmMapper filmMapper;
    FilmFilterDTO filmFilterDTO;
    RestTemplateClient restTemplateClient;
    RestTemplate restTemplate;
    String URI;
    String params;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, FilmFilterDTO filmFilterDTO, RestTemplateClient restTemplateClient, RestTemplate restTemplate) {
        this.filmMapper = filmMapper;
        this.filmFilterDTO = filmFilterDTO;
        this.restTemplateClient = restTemplateClient;
        this.restTemplate = restTemplate;
    }
        public List<FilmDTO> getAllFilms(){
            return filmMapper.getAllFilms();
        }
        public List<FilmDTO> getByFilmName(String name) {
            return filmMapper.getByName(name);
        }
        public List<FilmDTO> getByFilmId(Long id){
            return filmMapper.getById(id);
        }
        public  <S extends Film> S save(S entity){
            return filmMapper.save(entity);
        }
        
        public String addParamsForSearch(String order, String type, String keyword){
        order = "YEAR";
        type = "FILM";
        keyword = "world";
        filmFilterDTO = new FilmFilterDTO(order, type, keyword);
        String params = restTemplateClient.getURI(filmFilterDTO);
        return params;
        }
        public List<Film> getAllFilmsByFilterFromKinopoisk(){
        String token = restTemplateClient.getKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<FilmDTO> film = restTemplate.exchange(restTemplateClient.getURI(filmFilterDTO), HttpMethod.GET, entity, FilmDTO.class, params);
        return (List<Film>) film.getBody();
        }
}

