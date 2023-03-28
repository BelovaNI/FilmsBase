package com.work.filmsbase.mapping;

import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmMapper {
    FilmRepository filmRepository;
    ModelMapper mapper;
    @Autowired
    public FilmMapper(FilmRepository filmRepository, ModelMapper mapper) {
        this.filmRepository = filmRepository;
        this.mapper = mapper;
    }
    public FilmDTO convertToDTO(Film film) {
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        FilmDTO filmDTO = mapper
                .map(film, FilmDTO.class);
        return filmDTO;
    }

    public Film convertToFilm(FilmDTO filmDTO){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Film film = mapper.map(filmDTO, Film.class);
        return film;
    }
    public List<FilmDTO> getAllFilms() {
        return ((List<Film>) filmRepository
                .findAll())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<FilmDTO> getByName(String name) {
        return ((List<Film>) filmRepository
                .findFilmByFilmName(name))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<FilmDTO> getById(Long id){
        return ((List<Film>) filmRepository
                .findFilmByFilmId(id))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public  <S extends Film> S save(S entity){
        return filmRepository.save(entity);
    }
}
