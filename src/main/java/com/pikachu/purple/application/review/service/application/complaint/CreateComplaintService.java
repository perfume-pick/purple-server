package com.pikachu.purple.application.review.service.application.complaint;

import com.pikachu.purple.application.review.port.in.complaint.CreateComplaintUseCase;
import com.pikachu.purple.application.review.port.in.complaint.SendComplaintUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.application.review.util.TokenGenerator;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateComplaintService implements CreateComplaintUseCase {

    private final ComplaintRepository complaintRepository;
    private final SendComplaintUseCase sendComplaintUseCase;

    @Transactional
    @Override
    public void create(
        Long userId,
        Long reviewId
    ) {
        Long complaintId = IdUtil.generateId();
        String token = TokenGenerator.generate();

        Complaint complaint = complaintRepository.create(
            complaintId,
            userId,
            reviewId,
            token
        );

        sendComplaintUseCase.send(complaint);
    }

}
