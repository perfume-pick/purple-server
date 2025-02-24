package com.pikachu.purple.application.review.port.in.complaint;

import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Review;
import java.time.Instant;

public interface GetComplaintFormUseCase {

    Result invoke(
        Long complaintId,
        String token
    );

    record Result(ComplaintFormDTO complaintFormDTO) {}

    record ComplaintFormDTO(
        Instant reportedAt,
        Long reporterId,
        Long reportedId,
        Long perfumeId,
        String perfumeName,
        String comment,
        String link
    ) {

        public static ComplaintFormDTO from(
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

}
