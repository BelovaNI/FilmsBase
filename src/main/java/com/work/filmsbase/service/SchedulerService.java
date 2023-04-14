package com.work.filmsbase.service;

import javax.mail.MessagingException;

public interface SchedulerService {
    void sendScheduledEmail() throws MessagingException, InterruptedException;
}
