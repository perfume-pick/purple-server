package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.review.port.in.complaint.SendComplaintUseCase;
import com.pikachu.purple.application.review.port.out.MailSender;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SendComplaintService implements SendComplaintUseCase {

    private final MailSender mailSender;

    @Override
    public void send(Complaint complaint) {
        mailSender.send(
            complaint.getId(),
            complaint.getToken()
        );
    }

}
