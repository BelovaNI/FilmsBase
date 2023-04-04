package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.DTO.FilmGetResponseDTO;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class FilmServiceImpl implements FilmService{
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
    @Override
    public <S extends Film> S save(S entity) {
        return filmRepository.save(entity);
    }
    @Override
    public List<Film> getAllFilmsByFilterFromKinopoisk(FilmFilterDTO filmFilterDTO) {
        ResponseEntity<FilmGetResponseDTO> responseEntity = restTemplateClient.getAllFilmsByFilterFromKinopoisk(filmFilterDTO);
        try {
            if (responseEntity.hasBody()) {
                return responseEntity.getBody().getFilms().stream().map(filmMapper::convertToFilm).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Film> copyFilmsInDataBase(List<Film> list) {
        List<Film> sendList = new ArrayList<>();
        for (Film film: list) {
            if (!filmRepository.existsFilmByFilmId(film.getFilmId())) {
                save(film);
                sendList.add(film);
            }
        }
        return sendList;
    }
    @Override
    public List<Film> searchFromDataBase(FilmDTO filmDTO, PageRequest pageRequest){
        List <Film> resultList = new ArrayList<>();
        if(filmDTO.getFilmName() != null | filmDTO.getYear() != null | filmDTO.getRating() != null){
            List<Film> name = filmRepository.findFilmByFilmName(filmDTO.getFilmName());
            List<Film> year = filmRepository.findFilmByYear(filmDTO.getYear());
            List<Film> rating = filmRepository.findFilmsByRating(filmDTO.getRating());
            resultList.addAll(name);
            resultList.addAll(year);
            resultList.addAll(rating);
            return resultList;
        }
        return null;
    }
    @Override
    public List<Film> sortByParamNameAndDirection(List<Film> list, String name, String direction) {
    return null;
    }


}


