package com.work.filmsbase.service;
import com.work.filmsbase.jms.JmsProducer;
import com.work.filmsbase.model.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService{
    EntityManager em;
    EmailServiceImpl emailService;
    FilmServiceImpl filmService;
    JmsProducer jmsProducer;
    @Autowired
    public SchedulerServiceImpl(EmailServiceImpl emailService, FilmServiceImpl filmService, EntityManager em, JmsProducer jmsProducer) {
        this.emailService = emailService;
        this.filmService = filmService;
        this.em = em;
        this.jmsProducer=jmsProducer;
    }
    @Override
    public void sendScheduledEmail(String parameter){
        try {
            PageRequest pageRequest = PageRequest.ofSize(10);
            Query nativeQuery = em.createNativeQuery("SELECT * FROM public.film WHERE viewed = 'false' AND genres @> CAST(:genre AS jsonb)", Film.class);
            nativeQuery.setParameter("genre", parameter);
            List<Film> films = nativeQuery.setFirstResult((int) pageRequest.getOffset()).setMaxResults(pageRequest.getPageSize()).getResultList();
            emailService.sendEmailForEveryDayOfWeekWithAttachment(films);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
