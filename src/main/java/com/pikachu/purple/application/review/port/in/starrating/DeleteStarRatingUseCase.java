package com.pikachu.purple.application.review.port.in.starrating;

import com.pikachu.purple.domain.review.StarRating;

public interface DeleteStarRatingUseCase {

    void invoke(Command command);

    record Command(Long starRatingId) {}

}
