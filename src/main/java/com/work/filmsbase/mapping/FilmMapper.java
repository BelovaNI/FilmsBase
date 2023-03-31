package com.work.filmsbase.mapping;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.model.Film;
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
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        FilmDTO filmDTO = mapper.map(film, FilmDTO.class);
        return filmDTO;
    }

    public Film convertToFilm(FilmDTO filmDTO){
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Film film = mapper.map(filmDTO, Film.class);
        return film;
    }
}
