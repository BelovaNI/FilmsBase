package com.work.filmsbase.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RequestMapping("")
@RestController
public class FilmController {
    FilmServiceImpl filmService;
    @Autowired
    public FilmController(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public ResponseEntity<?> getResponse(){
        try{
            ResponseEntity<?> response = filmService.getAllFilmsByFilterFromKinopoisk();
            Film films = new ObjectMapper().readValue((JsonParser) response.getBody(), Film.class);
            filmService.save(films);
        }catch(Exception ex){
            String errorMessage;
            errorMessage ="Not found films" + ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    }

