package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.domain.review.Review;
import java.util.List;

public interface GetReviewsDetailWithEvaluationByUpdatedDateUseCase {


    Result invoke(Command command);

    record Command(String updatedDate) {}

    record Result(List<Review> reviews) {}

}
