package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.review.port.in.SendComplaintUseCase;
import com.pikachu.purple.application.review.port.out.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendComplaintApplicationService implements SendComplaintUseCase {

    private final MailSender mailSender;

    @Override
    public void invoke(Command command) {
        mailSender.send(
            command.complaint().getId(),
            command.complaint().getToken()
        );
    }

}
