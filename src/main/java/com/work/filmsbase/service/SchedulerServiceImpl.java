package com.work.filmsbase.service;
import com.work.filmsbase.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{
    EntityManager em;
    EmailServiceImpl emailService;
    FilmServiceImpl filmService;
    @Autowired
    public SchedulerServiceImpl(EmailServiceImpl emailService, FilmServiceImpl filmService, EntityManager em) {
        this.emailService = emailService;
        this.filmService = filmService;
        this.em = em;
    }
    @Override
    @Scheduled(cron = "16 00 * * * *")
    public void sendScheduledEmail(){
        try {
            LocalDate date = LocalDate.now();
            DayOfWeek day = date.getDayOfWeek();
            String parameter = null;
            PageRequest pageRequest = PageRequest.ofSize(10);
            if (day == DayOfWeek.MONDAY) {
                parameter = "[{\"genre\":\"фантастика\"}]";
            }
            if (day == DayOfWeek.TUESDAY) {
                parameter = "[{\"genre\":\"комедия\"}]";
            }
            if (day == DayOfWeek.WEDNESDAY) {
                parameter = "[{\"genre\":\"семейный\"}]";
            }
            if (day == DayOfWeek.THURSDAY) {
                parameter = "[{\"genre\":\"боевик\"}]";
            }
            if (day == DayOfWeek.FRIDAY) {
                parameter = "[{\"genre\":\"фэнтези\"}]";
            }
            if (day == DayOfWeek.SATURDAY) {
                parameter = "[{\"genre\":\"триллер\"}]";
            }
            if (day == DayOfWeek.SUNDAY) {
                parameter = "[{\"genre\":\"драма\"}]";
            }
            Query nativeQuery = em.createNativeQuery("SELECT * FROM public.film WHERE viewed = 'false' AND genres @> CAST(:genre AS jsonb)", Film.class);
            nativeQuery.setParameter("genre", parameter);
            List<Film> films = nativeQuery.setFirstResult((int) pageRequest.getOffset()).setMaxResults(pageRequest.getPageSize()).getResultList();
            emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
