package com.pikachu.purple.application.review.service.complaint;

import com.pikachu.purple.application.perfume.port.in.perfume.GetPerfumeUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase;
import com.pikachu.purple.application.review.port.in.complaint.GetComplaintFormUseCase.ComplaintFormDTO;
import com.pikachu.purple.application.review.port.out.ComplaintRepository;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetComplaintFormService implements GetComplaintFormUseCase {

    private final GetPerfumeUseCase getPerfumeUseCase;

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

        Review review = reviewRepository.find(complaint.getReview().getId());
        Perfume perfume = getPerfumeUseCase.find(review.getPerfume().getId()).perfume();

        ComplaintFormDTO complaintFormDTO = this.mapToComplaintFormDTO(
            complaint,
            perfume,
            review,
            complaintUri
        );

        return new Result(complaintFormDTO);
    }

    public ComplaintFormDTO mapToComplaintFormDTO(
        Complaint complaint,
        Perfume perfume,
        Review reportedReview,
        String reviewUri
    ) {
        return new ComplaintFormDTO(
            complaint.getReportedAt(),
            complaint.getId(),
            reportedReview.getUser().getId(),
            perfume.getId(),
            perfume.getName(),
            reportedReview.getContent(),
            reviewUri + "/" + complaint.getId() + "?token=" + complaint.getToken()
        );
    }

}
