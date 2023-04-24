package com.work.filmsbase.jms;
import com.work.filmsbase.service.SchedulerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.Message;
import javax.jms.MessageListener;


@Component
@Slf4j
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class JmsConsumer implements MessageListener {
    SchedulerServiceImpl schedulerService;
    @Autowired
    public JmsConsumer(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }

    @Override
    @JmsListener(destination = "demo.topic")
    public void onMessage(Message message) {
        try{
            Message ms = message;
            String task = ms.toString();
            if(ms != null) {
                log.info("Получена задача из топика: " + task.toString());
                schedulerService.sendScheduledEmail();
            }
        } catch(Exception e) {
            log.error("Получено исключение при обработке сообщения: "+ e);
        }

    }
}
