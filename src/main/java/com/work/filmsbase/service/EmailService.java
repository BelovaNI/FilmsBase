package com.work.filmsbase.service;

import com.work.filmsbase.model.Film;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmailWithAttachment(List<Film> list) throws MessagingException;
}
