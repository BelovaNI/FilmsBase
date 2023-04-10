package com.work.filmsbase.service;
import com.work.filmsbase.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    public JavaMailSender emailSender;
    private String toAddress = "nadine241197@gmail.com";
    private String subject = "List of films";
    private String message = "This films saved in your films base";
    private String from = "filmsbase6@gmail.com";

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmailWithAttachment(List<Film> list) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
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
}
