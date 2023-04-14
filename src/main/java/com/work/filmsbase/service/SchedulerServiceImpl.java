package com.work.filmsbase.service;
import com.work.filmsbase.DTO.FilmDTO;
import com.work.filmsbase.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{
    EmailServiceImpl emailService;
    FilmServiceImpl filmService;
    @Autowired
    public SchedulerServiceImpl(EmailServiceImpl emailService, FilmServiceImpl filmService) {
        this.emailService = emailService;
        this.filmService = filmService;
    }
    @Scheduled(fixedRate = 300000)
    @Override
    public void sendScheduledEmail() throws MessagingException, InterruptedException {
        LocalDate date = LocalDate.now();
        DayOfWeek day = date.getDayOfWeek();
        try {
            if (day == DayOfWeek.MONDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("%"+"фантастика"+"%", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.TUESDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("комедия", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.WEDNESDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("семейный", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.THURSDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("боевик", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.FRIDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("фэнтези", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.SATURDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("триллер", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
            if (day == DayOfWeek.SUNDAY) {
                List<Film> films = filmService.findFilmsByGenresAndViewed("драма", false);
                emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(400000);
    }
}
