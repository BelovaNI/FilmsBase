package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Service
@NoArgsConstructor
public class FilmServiceImpl {
    FilmMapper filmMapper;
    FilmFilterDTO filmFilterDTO;
    RestTemplateClient restTemplateClient;
    RestTemplate restTemplate;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, FilmFilterDTO filmFilterDTO, RestTemplateClient restTemplateClient, RestTemplate restTemplate) {
        this.filmMapper = filmMapper;
        this.filmFilterDTO = filmFilterDTO;
        this.restTemplateClient = restTemplateClient;
        this.restTemplate = restTemplate;
    }
        public List<FilmDTO> getAllFilms(){
            return filmMapper.getAllFilms();
        }
        public List<FilmDTO> getByFilmName(String name) {
            return filmMapper.getByName(name);
        }
        public List<FilmDTO> getByFilmId(Long id){
            return filmMapper.getById(id);
        }
        public  <S extends Film> S save(S entity){
            return filmMapper.save(entity);
        }
        
        public List<Film> addParamsForSearch(FilmFilterDTO filmFilterDTO){
        List<Film> params;
        filmFilterDTO = new FilmFilterDTO("YEAR", "FILM", "world");
        params = restTemplateClient.getAllFilmsByFilterFromKinopoisk(filmFilterDTO);
        return params;
        }
}

