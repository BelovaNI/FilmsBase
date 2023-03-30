package com.work.filmsbase.repository;

import com.work.filmsbase.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAll();
    Film findFilmByFilmName(String name);
    Film findFilmByFilmId(Long id);
    @Override
    <S extends Film> S save(S entity);
}
