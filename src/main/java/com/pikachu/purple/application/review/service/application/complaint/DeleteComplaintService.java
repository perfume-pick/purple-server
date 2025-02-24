package com.pikachu.purple.application.review.service.application.complaint;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DeleteComplaintService implements DeleteComplaintUseCase {

    private final ComplaintRepository complaintRepository;
    private final DeleteReviewUseCase deleteReviewUseCase;

    @Override
    @Transactional
    public void delete(
        Long userId,
        Long reviewId
    ) {
        Complaint complaint = complaintRepository.find(
            userId,
            reviewId
        );

        complaintRepository.delete(complaint.getId());
    }

    @Override
    public void deleteWithReview(
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
