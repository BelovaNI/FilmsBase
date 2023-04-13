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
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@NoArgsConstructor
public class FilmServiceImpl implements FilmService{
    FilmMapper filmMapper;
    RestTemplateClient restTemplateClient;
    FilmRepository filmRepository;
    EntityManager entityManager;
    JavaMailSender javaMailSender;
    @Autowired
    public FilmServiceImpl(FilmMapper filmMapper, RestTemplateClient restTemplateClient, FilmRepository filmRepository, EntityManager entityManager) {
        this.filmMapper = filmMapper;
        this.restTemplateClient = restTemplateClient;
        this.filmRepository = filmRepository;
        this.entityManager = entityManager;
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
    public List<Film> searchFromDataBase(FilmDTO filmDTO, PageRequest pageRequest) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
            Root<Film> filmRoot = criteriaQuery.from(Film.class);
            List<Predicate> predicates = new ArrayList<>();
            if (filmDTO.getFilmName() != null) {
                Predicate predicateForName = criteriaBuilder.like(filmRoot.get("filmName"), "%" + filmDTO.getFilmName() + "%");
                predicates.add(predicateForName);
            }
            if (filmDTO.getYear() != null) {
                Predicate predicateForYear = criteriaBuilder.equal(filmRoot.get("year"), filmDTO.getYear());
                predicates.add(predicateForYear);
            }
            if (filmDTO.getRating() != null) {
                Predicate predicateForRating = criteriaBuilder.equal(filmRoot.get("rating"), filmDTO.getRating());
                predicates.add(predicateForRating);
            }
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            criteriaQuery.orderBy(criteriaBuilder.desc(filmRoot.get("filmId")));
            List<Film> films = entityManager.createQuery(criteriaQuery).setFirstResult((int) pageRequest.getOffset()).setMaxResults(pageRequest.getPageSize()).getResultList();
            return films;
    }

    @Override
    public Film markFilmAsViewed(Long filmId) {
        Film film = filmRepository.findFilmsByFilmId(filmId);
        film.setViewed(true);
        filmRepository.save(film);
        return film;
    }
}


