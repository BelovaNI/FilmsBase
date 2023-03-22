package com.work.filmsbase.service;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FilmServiceClient {
    FilmRepository filmRepository;
    RestTemplate restTemplate;
    Environment env;
    @Autowired
    public FilmServiceClient(FilmRepository filmRepository, RestTemplate restTemplate) {
        this.filmRepository = filmRepository;
        this.restTemplate = restTemplate;
    }
    public void deleteById(Long id){
        filmRepository.deleteFilmsById(id);
    }
    public List<Film> getAllFilms(){
        return filmRepository.readAllBy();
    }
    public Film getByName(String name) {
        return filmRepository.findFilmByFilmName(name);
    }
    public Film getById(Long id){
        return filmRepository.findFilmByFilmId(id);
    }
    public <S extends Film> List<Film> save(S film){
        return (List<Film>) filmRepository.save(film);
    }

    public ResponseEntity<String> executeRestCall() {
        String URL = env.getProperty("api.url");
        String token = env.getProperty("api.key");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> film = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class, 1);
        return film;
    }

}
