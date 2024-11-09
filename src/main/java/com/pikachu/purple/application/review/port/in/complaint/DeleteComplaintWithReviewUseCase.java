package com.pikachu.purple.application.review.port.in.complaint;

public interface DeleteComplaintWithReviewUseCase {

    void invoke(Command command);

    record Command(
        Long complaintId,
        String token
    ) {}

}
