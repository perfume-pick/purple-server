package com.pikachu.purple.application.review.port.in.complaint;

public interface DeleteComplaintWithReviewUseCase {

    void invoke(
        Long complaintId,
        String token
    );

}
