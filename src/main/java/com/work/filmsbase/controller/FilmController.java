package com.work.filmsbase.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.FilmServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("")
public class FilmController {
    FilmServiceClient filmServiceClient;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public FilmController(FilmServiceClient filmServiceClient) {
        this.filmServiceClient = filmServiceClient;
    }
    @PostConstruct
    @RequestMapping (value = "/films", method = RequestMethod.GET)
    public String saveToDataBase() throws JsonProcessingException {
        ResponseEntity<String> list = filmServiceClient.executeRestCall();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Film film = objectMapper.readValue(list.getBody(), Film.class);
        if(film != null) {
            filmServiceClient.save(film);
        }else {
            return "FFFailed!";
        }
        return "Save is successfully";
    }
}
