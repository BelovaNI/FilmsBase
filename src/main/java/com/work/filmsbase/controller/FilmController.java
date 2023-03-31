package com.work.filmsbase.controller;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@RequestMapping("")
@RestController
@Slf4j
public class FilmController {
    FilmServiceImpl filmService;
    FilmMapper filmMapper;
    @Autowired
    public FilmController(FilmServiceImpl filmService, FilmMapper filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @RequestMapping(value = "/films/search-by-keyword", method = RequestMethod.GET)
    public ResponseEntity<?> getResponse(@ModelAttribute FilmFilterDTO filmFilterDTO){
        try{
            List<Film> response = filmService.getAllFilmsByFilterFromKinopoisk(filmFilterDTO);
            log.info("response is {}", response);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            String errorMessage;
            errorMessage ="Фильмы по данному запросу не найдены " + ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    }

