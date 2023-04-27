package com.work.filmsbase.jms;
import com.work.filmsbase.DTO.GenreFilterDTO;
import com.work.filmsbase.configuration.ConfigProperties;
import com.work.filmsbase.configuration.SchedulerConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
import java.time.LocalDate;


@Component
@Slf4j
@Data
public class JmsProducer {
    JmsTemplate jmsTemplate;
    SchedulerConfig schedulerConfig;
    ConfigProperties configProperties;
    GenreFilterDTO genreFilterDTO = new GenreFilterDTO();
    @Value("demo.topic")
    private String topic;
    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate, SchedulerConfig schedulerConfig, ConfigProperties configProperties) {
        this.jmsTemplate = jmsTemplate;
        this.schedulerConfig = schedulerConfig;
        this.configProperties = configProperties;
    }
    @Scheduled(cron ="${cron.value}")
    public void sendMessage(){
        try {
            LocalDate date = LocalDate.now();
            DayOfWeek day = date.getDayOfWeek();
            String parameter = null;
            if (day == DayOfWeek.MONDAY) {
                parameter = configProperties.getMon(); //фантастика
            }
            if (day == DayOfWeek.TUESDAY) {
                parameter = configProperties.getTue(); //комедия
            }
            if (day == DayOfWeek.WEDNESDAY) {
                parameter = configProperties.getWed(); //семейный
            }
            if (day == DayOfWeek.THURSDAY) {
                parameter = configProperties.getThu(); //боевик
            }
            if (day == DayOfWeek.FRIDAY) {
                parameter = configProperties.getFri(); //фэнтези
            }
            if (day == DayOfWeek.SATURDAY) {
                parameter = configProperties.getSat(); //триллер
            }
            if (day == DayOfWeek.SUNDAY) {
                parameter = configProperties.getSun(); //драма
            }
            genreFilterDTO.setGenres(parameter); //ДТО с фильтром жанров
            log.info("Отправить задачу в топик: "+ topic);
            jmsTemplate.convertAndSend(topic, genreFilterDTO);
        } catch(Exception e){
            log.error("При отправке задачи получена следующая ошибка: ", e);
        }
    }
}
