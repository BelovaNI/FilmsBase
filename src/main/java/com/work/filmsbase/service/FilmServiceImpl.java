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
    Map<String, String> params;
    String URI;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, FilmFilterDTO filmFilterDTO, RestTemplateClient restTemplateClient) {
        this.filmMapper = filmMapper;
        this.filmFilterDTO = filmFilterDTO;
        this.restTemplateClient = restTemplateClient;
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
        
        public String addParamsForSearch(FilmFilterDTO filmFilterDTO){
        filmFilterDTO = new FilmFilterDTO("YEAR", "FILM", "world");
        String URI = restTemplateClient.getComponentsBuilder(filmFilterDTO);
        return URI;
        }
        public ResponseEntity<String> getAllFilmsByFilterFromKinopoisk(){
        String token = restTemplateClient.getKey();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> film = restTemplateClient.executeRestCall(URI, HttpMethod.GET, entity, String.class, params);
        return film;
        }
}

