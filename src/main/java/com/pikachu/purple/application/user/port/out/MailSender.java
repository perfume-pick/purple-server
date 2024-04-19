package com.pikachu.purple.application.user.port.out;

import org.springframework.scheduling.annotation.Async;

public interface MailSender {

    @Async
    void send(
        String email,
        String content
    );

}
