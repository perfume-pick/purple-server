package com.pikachu.purple.application.review.service.application.complaint;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.SendComplaintUseCase;
import com.pikachu.purple.application.review.port.in.complaint.CreateComplaintUseCase;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateComplaintApplicationService implements CreateComplaintUseCase {

    private final ComplaintDomainService complaintDomainService;
    private final SendComplaintUseCase sendComplaintUseCase;

    @Transactional
    @Override
    public void invoke(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();
        Complaint complaint = complaintDomainService.create(
            userId,
            reviewId
        );

        sendComplaintUseCase.invoke(complaint);
    }

}
