package com.work.filmsbase.controller;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.EmailServiceImpl;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@RequestMapping("")
@RestController
@Slf4j
public class FilmController {
    FilmServiceImpl filmService;
    EmailServiceImpl emailService;

    @Autowired
    public FilmController(FilmServiceImpl filmService, EmailServiceImpl emailService) {
        this.filmService = filmService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/films/search-by-keyword", method = RequestMethod.GET)
    public ResponseEntity<?> getResponse(@ModelAttribute FilmFilterDTO filmFilterDTO) {
        try {
            List<Film> response = filmService.getAllFilmsByFilterFromKinopoisk(filmFilterDTO);
            List<Film> sendList = filmService.copyFilmsInDataBase(response);
            if (!sendList.isEmpty()) {
                emailService.sendEmailWithAttachment(sendList);
            }
            log.info("response is {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = "Фильмы по данному запросу не найдены " + ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Film> searchFilmInDB(@ModelAttribute FilmDTO filmDTO, FilmFilterDTO filmFilterDTO,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(name = "size", required = false, defaultValue = "2") int size) throws NullPointerException {
        try {
            List<Film> answer = filmService.searchFromDataBase(filmDTO, PageRequest.of(page, size));
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/mark", method = RequestMethod.GET)
    public String markFilmAsViewed(Long filmId) {
        try {
            Optional<Long> optLong = Optional.ofNullable(filmId);
            Long value = optLong.orElse(328L);
            Film film1 = filmService.markFilmAsViewed(value);
            return film1.toString();
        } catch (Exception e) {
            String errorMessage;
            errorMessage = "Фильм с данным идентификатором не найден в базе данных  " + e + " <== error";
            return String.valueOf(new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND));
        }
    }
}

