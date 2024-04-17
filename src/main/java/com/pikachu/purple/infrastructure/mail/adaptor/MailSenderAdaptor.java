package com.pikachu.purple.infrastructure.mail.adaptor;

import com.pikachu.purple.application.user.port.out.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderAdaptor implements MailSender {
    private final JavaMailSender javaMailSender;
    @Override
    @Async
    public void send(String email, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);

        message.setSubject("[Perpicks]" + " 이메일 인증번호를 확인하세요.");
        message.setText("인증번호는 " + content + " 입니다.");
        javaMailSender.send(message);
    }
}
