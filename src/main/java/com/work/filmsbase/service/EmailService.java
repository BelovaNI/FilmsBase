package com.work.filmsbase.service;

import com.work.filmsbase.DTO.MailDTO;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmailWithAttachment(MailDTO mailDTO, String attachment) throws MessagingException;
}
