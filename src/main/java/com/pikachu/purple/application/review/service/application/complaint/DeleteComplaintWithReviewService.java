package com.pikachu.purple.application.review.service.application.complaint;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintWithReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteComplaintWithReviewService implements
    DeleteComplaintWithReviewUseCase {

    private final ComplaintRepository complaintRepository;
    private final DeleteReviewUseCase deleteReviewUseCase;

    @Transactional
    @Override
    public void invoke(
        Long complaintId,
        String token
    ) {
        Complaint complaint = complaintRepository.find(
            complaintId,
            token
        );
        complaintRepository.delete(complaintId);
        deleteReviewUseCase.invoke(complaint.getReview().getId());
    }

}
