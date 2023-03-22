package com.work.filmsbase.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.FilmServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class FilmController {
    FilmServiceClient filmServiceClient;
    ObjectMapper objectMapper;
    @Autowired
    public FilmController(FilmServiceClient filmServiceClient) {
        this.filmServiceClient = filmServiceClient;
    }
    @RequestMapping ("/films")
    public String saveToDataBase() throws JsonProcessingException {
        ResponseEntity<String> list = filmServiceClient.executeRestCall();
        Film film = objectMapper.readValue(list.getBody(), Film.class);
        if(film != null) {
            filmServiceClient.save(film);
        }else {
            return "FFFailed!";
        }
        return "Save is successfully";
    }
}
