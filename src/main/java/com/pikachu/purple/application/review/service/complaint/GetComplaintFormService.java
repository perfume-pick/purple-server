package com.pikachu.purple.application.review.service.complaint;

import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetComplaintFormService implements GetComplaintFormUseCase {

    private final ComplaintRepository complaintRepository;
    private final ReviewRepository reviewRepository;

    @Value(value = "${uri.server-complaint}")
    private String complaintUri;

    @Transactional
    @Override
    public Result invoke(
        Long complaintId,
        String token
    ) {
        Complaint complaint = complaintRepository.find(
            complaintId,
            token
        );

        Review review = reviewRepository.findWithPerfume(complaint.getReview().getId());

        ComplaintFormDTO complaintFormDTO = this.mapToComplaintFormDTO(
            review,
            complaint,
            complaintUri
        );

        return new Result(complaintFormDTO);
    }

    public ComplaintFormDTO mapToComplaintFormDTO(
        Review reportedReview,
        Complaint complaint,
        String reviewUri
    ) {
        return new ComplaintFormDTO(
            complaint.getReportedAt(),
            complaint.getId(),
            reportedReview.getUser().getId(),
            reportedReview.getPerfume().getId(),
            reportedReview.getPerfume().getName(),
            reportedReview.getContent(),
            reviewUri + "/" + complaint.getId() + "?token=" + complaint.getToken()
        );
    }

}
