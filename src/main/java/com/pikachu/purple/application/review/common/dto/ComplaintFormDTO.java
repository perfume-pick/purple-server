package com.pikachu.purple.application.review.common.dto;

import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.domain.review.Review;
import java.time.Instant;

public record ComplaintFormDTO(
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
