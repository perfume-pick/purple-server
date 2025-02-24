package com.pikachu.purple.application.review.service.application.complaint;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.complaint.DeleteComplaintUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.domain.review.Complaint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DeleteComplaintService implements DeleteComplaintUseCase {

    private final ComplaintRepository complaintRepository;

    @Override
    public void invoke(Long reviewId) {
        Long userId = getCurrentUserAuthentication().userId();

        Complaint complaint = complaintRepository.find(
            userId,
            reviewId
        );

        complaintRepository.delete(complaint.getId());
    }

}
