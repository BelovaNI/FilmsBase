package com.work.filmsbase.repository;
import com.work.filmsbase.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    boolean existsFilmByFilmId(Long filmId);
    @Override
    <S extends Film> S save(S entity);
    Film findFilmsByFilmId(Long filmId);
    List<Film> findFilmsByGenresAndViewed(String genres, Boolean viewed);

}
