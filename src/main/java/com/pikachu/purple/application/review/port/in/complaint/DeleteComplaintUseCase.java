package com.pikachu.purple.application.review.port.in.complaint;

public interface DeleteComplaintUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {}

}
