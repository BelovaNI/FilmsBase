package controller;
import com.work.filmsbase.DTO.FilmFilterDTO;
import com.work.filmsbase.controller.FilmController;
import com.work.filmsbase.model.Film;
import com.work.filmsbase.service.EmailServiceImpl;
import com.work.filmsbase.service.FilmServiceImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@Getter
@ExtendWith(MockitoExtension.class)
@Slf4j
public class FilmControllerTest {
    FilmServiceImpl filmServiceMock;
    EmailServiceImpl emailServiceMock;
    FilmController filmController;
    @Before
    public void setUp(){
        filmServiceMock = Mockito.mock(FilmServiceImpl.class);
        emailServiceMock = Mockito.mock(EmailServiceImpl.class);
        filmController = new FilmController(filmServiceMock, emailServiceMock);
    }
    @Test
    public void http_status_is_ok(){
        List<Film> films = new ArrayList<>();
        Film film = new Film();
        films.add(film);
        FilmFilterDTO filmFilterDTO = new FilmFilterDTO();
        when(filmServiceMock.getAllFilmsByFilterFromKinopoisk(filmFilterDTO)).thenReturn(films);
        when(filmServiceMock.copyFilmsInDataBase(films)).thenReturn(films);
        assertEquals(filmController.getResponse(filmFilterDTO).getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void throw_null_pointer_exception(){
        when(filmServiceMock.markFilmAsViewed(1L)).thenReturn(null);
        assertEquals("<404 NOT_FOUND Not Found,Фильм с данным идентификатором не найден в базе данных  java.lang.NullPointerException <== error,[]>",
                filmController.markFilmAsViewed(1L));
    }
}
