package com.work.filmsbase.repository;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    boolean existsFilmByFilmId(Long filmId);
    @Override
    <S extends Film> S save(S entity);
    Film findFilmsByFilmId(Long filmId);

}
