package com.work.filmsbase.service;
import com.work.filmsbase.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    public JavaMailSender emailSender;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @Override
    public void sendEmailWithAttachment(List<Film> list) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("filmsbase6@gmail.com");
            messageHelper.setTo("nadine241197@gmail.com");
            messageHelper.setSubject("List of films");
            messageHelper.setText("This films saved in your films base");
            File csvFile = new File("films.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            for(Film f: list) {
                fileWriter.write(f.toString());
            }
            fileWriter.close();
            messageHelper.addAttachment("Films list", csvFile);
            emailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailForEveryDayOfWeekWithAttachment(List<Film> list) throws MessagingException {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("filmsbase6@gmail.com");
            messageHelper.setTo("nadine241197@gmail.com");
            messageHelper.setSubject("Daily list of new films");
            messageHelper.setText("This is new films for your pleasure");
            File csvFile = new File("new films.csv");
            FileWriter fileWriter = new FileWriter(csvFile);
            for(Film f: list) {
                fileWriter.write(f.toString());
            }
            fileWriter.close();
            messageHelper.addAttachment("Films list", csvFile);
            emailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
