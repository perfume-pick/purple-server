package com.pikachu.purple.application.review.service.application.complaint;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteComplaintApplicationService implements DeleteComplaintUseCase {

    private final ComplaintDomainService complaintDomainService;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Complaint complaint = complaintDomainService.find(
            userId,
            command.reviewId()
        );

        complaintDomainService.delete(complaint.getId());
    }

}
