package com.pikachu.purple.application.review.service;

import com.pikachu.purple.application.review.port.in.complaint.SendComplaintUseCase;
import com.pikachu.purple.application.review.port.out.MailSender;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class SendComplaintService implements SendComplaintUseCase {

    private final MailSender mailSender;

    @Transactional
    @Override
    public void send(Complaint complaint) {
        mailSender.send(
            complaint.getId(),
            complaint.getToken()
        );
    }

}
