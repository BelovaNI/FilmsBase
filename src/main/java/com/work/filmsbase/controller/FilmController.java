package com.work.filmsbase.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.filmsbase.DTO.FilmFilterDTO;
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
            List<Film> response = filmService.getAllFilmsByFilterFromKinopoisk();
            System.out.println(response);
        }catch(Exception ex){
            String errorMessage;
            errorMessage ="Фильмы по данному запросу не найдены " + ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    }

