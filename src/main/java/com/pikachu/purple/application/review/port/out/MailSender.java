package com.pikachu.purple.application.review.port.out;

public interface MailSender {

    void send(
        Long complaintId,
        String token
    );

}
