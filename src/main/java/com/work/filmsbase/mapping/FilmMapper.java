package com.work.filmsbase.mapping;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
    ModelMapper mapper;
    @Autowired
    public FilmMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public FilmDTO convertToDTO(Film film) {
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        FilmDTO filmDTO = mapper.map(film, FilmDTO.class);
        return filmDTO;
    }

    public Film convertToFilm(FilmDTO filmDTO){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Film film = mapper.map(filmDTO, Film.class);
        if (filmDTO.getKinopoiskId() != null
                && filmDTO.getNameEn() != null && filmDTO.getYear() != null
        && filmDTO.getRatingKinopoisk() != null && filmDTO.getShortDescription() != null) {
            film.setFilmId(filmDTO.getKinopoiskId());
            film.setFilmName(filmDTO.getNameEn());
            film.setYear(filmDTO.getYear());
            film.setRating(filmDTO.getRatingKinopoisk());
            film.setDescription(filmDTO.getShortDescription());
        }
        return film;
    }
}
