package com.pikachu.purple.infrastructure.mail.adaptor;

import com.pikachu.purple.application.review.port.out.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderAdaptor implements MailSender {

    private final JavaMailSender javaMailSender;

    @Value(value = "${mail.username}")
    private String emailAddress;

    @Value(value = "${uri.server-complaint}")
    private String complaintUri;

    @Async
    @Override
    public void send(
        Long complaintId,
        String token
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);

        message.setSubject("[Perpicks] 신고 내역을 확인해주세요.");
        message.setText(complaintUri + "/" + complaintId + "?token=" + token);

        javaMailSender.send(message);
    }

}
