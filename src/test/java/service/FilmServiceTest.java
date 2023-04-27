package service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.DTO.FilmGetResponseDTO;
import com.work.filmsbase.client.RestTemplateClient;
import com.work.filmsbase.mapping.FilmMapper;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.repository.FilmRepository;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Getter
@ExtendWith(MockitoExtension.class)
@Slf4j
public class FilmServiceTest {
    private FilmServiceImpl filmService;
    FilmMapper filmMapperMock;
    RestTemplateClient restTemplateClientMock;
    FilmRepository repositoryMock;
    EntityManager entityManagerMock;
    @Before
    public void setUp(){
        repositoryMock = Mockito.mock(FilmRepository.class);
        filmMapperMock = Mockito.mock(FilmMapper.class);
        restTemplateClientMock = Mockito.mock(RestTemplateClient.class);
        entityManagerMock = Mockito.mock(EntityManager.class);
        filmService = new FilmServiceImpl(filmMapperMock, restTemplateClientMock, repositoryMock, entityManagerMock);
    }
    @Test
    public void test_list_not_empty(){
        FilmFilterDTO filmFilterDTO = new FilmFilterDTO("люди", 1);
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmName("Люди Х");
        filmDTO.setFilmId(2L);
        List<FilmDTO> films = new ArrayList<>();
        films.add(filmDTO);
        FilmGetResponseDTO filmGetResponseDTO = new FilmGetResponseDTO("люди",  12,  films);
        ResponseEntity<FilmGetResponseDTO> responseEntity = new ResponseEntity<>(filmGetResponseDTO, HttpStatus.OK);
        when(restTemplateClientMock.getAllFilmsByFilterFromKinopoisk(filmFilterDTO)).thenReturn(responseEntity);
        Film film = new Film();
        when(filmMapperMock.convertToFilm(filmDTO)).thenReturn(film);
        assertNotNull(filmService.getAllFilmsByFilterFromKinopoisk(filmFilterDTO));
    }
    @Test
    public void test_identical_lists(){
        Film film = new Film(1L, 11L, "Люди", 2001, 1.0, null, null, false);
        List<Film> films = new ArrayList<>();
        films.add(film);
        when(repositoryMock.existsFilmByFilmId(11L)).thenReturn(false);
        assertEquals(films, filmService.copyFilmsInDataBase(films));
    }


}
