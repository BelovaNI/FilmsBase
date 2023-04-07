package com.work.filmsbase.service;
import com.work.filmsbase.DTO.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{
    public JavaMailSender emailSender;
    public MailDTO mailDTO;
    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, MailDTO mailDTO) {
        this.emailSender = emailSender;
        this.mailDTO = mailDTO;
    }
    @Override
    public void sendEmailWithAttachment(MailDTO mailDTO, String attachment) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(mailDTO.getToAddress());
        messageHelper.setSubject(mailDTO.getSubject());
        messageHelper.setText(mailDTO.getMessage());
        messageHelper.setCc(mailDTO.getAttachment());
        emailSender.send(mimeMessage);
    }
}
