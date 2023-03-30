package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class FilmServiceImpl {
    FilmMapper filmMapper;
    FilmFilterDTO filmFilterDTO;
    RestTemplateClient restTemplateClient;
    FilmRepository filmRepository;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, FilmFilterDTO filmFilterDTO, RestTemplateClient restTemplateClient, FilmRepository filmRepository) {
        this.filmMapper = filmMapper;
        this.filmFilterDTO = filmFilterDTO;
        this.restTemplateClient = restTemplateClient;
        this.filmRepository = filmRepository;
    }
        public List<Film> getAllFilms(){
            return filmRepository.findAll();
        }
        public Film getByFilmName(String name) {return filmRepository.findFilmByFilmName(name);}
        public Film getByFilmId(Long id){
            return filmRepository.findFilmByFilmId(id);
        }
        public  <S extends Film> S save(S entity){
            return filmRepository.save(entity);
        }
        
        public List<Film> addParamsForSearch(){
        FilmFilterDTO filmFilterDTO = new FilmFilterDTO("YEAR", "FILM", "world");
        ResponseEntity<FilmDTO> params = restTemplateClient.getAllFilmsByFilterFromKinopoisk(filmFilterDTO);
        List<Film> list = new ArrayList<>();
        if(params.hasBody()) {
            Film film = filmMapper.convertToFilm(params.getBody());
            list.add(film);
        }
        return list;
        }
}
