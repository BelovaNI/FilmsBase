package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@NoArgsConstructor
public class FilmServiceImpl {
    FilmMapper filmMapper;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper) {
        this.filmMapper = filmMapper;
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
}

