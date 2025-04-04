package com.pikachu.purple.application.review.port.in.complaint;

public interface DeleteComplaintUseCase {

    void delete(Long userId, Long reviewId);

    void deleteWithReview(
        Long complaintId,
        String token
    );

}
