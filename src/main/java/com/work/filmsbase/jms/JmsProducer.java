package com.work.filmsbase.jms;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JmsProducer {
    JmsTemplate jmsTemplate;

    @Value("demo.topic")
    private String topic;
    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    @Scheduled(cron = "17 40 * * * *")
    public void sendMessage(){
        try{
            String message = "Отправить список фильмов";
            log.info("Отправить задачу в топик: "+ topic);
            jmsTemplate.convertAndSend(topic, message);
        } catch(Exception e){
            log.error("При отправке задачи получена следующая ошибка: ", e);
        }
    }
}
