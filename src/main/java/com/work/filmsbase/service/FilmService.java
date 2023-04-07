package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.model.Film;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FilmService {
    <S extends Film> S save(S entity);
    List<Film> getAllFilmsByFilterFromKinopoisk(FilmFilterDTO filmFilterDTO);
    List<Film> copyFilmsInDataBase(List<Film> list);
    List<Film> searchFromDataBase(FilmDTO filmDTO, PageRequest pageRequest);
}
