package com.pikachu.purple.application.review.port.in.starrating;

public interface DeleteStarRatingUseCase {

    void invoke(Command command);

    record Command(Long starRatingId) {}

}
