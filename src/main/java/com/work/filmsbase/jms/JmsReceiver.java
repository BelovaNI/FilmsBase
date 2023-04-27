package com.work.filmsbase.jms;
import com.work.filmsbase.DTO.GenreFilterDTO;
import com.work.filmsbase.service.SchedulerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class JmsReceiver {
    SchedulerServiceImpl schedulerService;
    @Autowired
    public JmsReceiver(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }

    @JmsListener(destination = "demo.topic")
    public void onMessage(GenreFilterDTO genreFilterDTO) {
        try{
            GenreFilterDTO object = genreFilterDTO;
            String genre = object.getGenres();
            schedulerService.sendScheduledEmail(genre);
            log.info("Получена задача из топика: " + genre);
        } catch(Exception e) {
            log.error("Получено исключение при обработке сообщения: "+ e);
        }

    }
}
