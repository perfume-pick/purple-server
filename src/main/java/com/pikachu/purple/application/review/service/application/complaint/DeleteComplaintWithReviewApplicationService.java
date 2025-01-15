package com.pikachu.purple.application.review.service.application.complaint;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintWithReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.service.domain.ComplaintDomainService;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteComplaintWithReviewApplicationService implements
    DeleteComplaintWithReviewUseCase {

    private final DeleteReviewUseCase deleteReviewUseCase;
    private final ComplaintDomainService complaintDomainService;

    @Transactional
    @Override
    public void invoke(
        Long complaintId,
        String token
    ) {
        Complaint complaint = complaintDomainService.find(
            complaintId,
            token
        );
        complaintDomainService.delete(complaintId);
        deleteReviewUseCase.invoke(complaint.getReview().getId());
    }

}
