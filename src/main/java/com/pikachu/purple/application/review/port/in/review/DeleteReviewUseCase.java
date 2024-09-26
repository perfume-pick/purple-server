package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.util.IdUtil;

public interface DeleteReviewUseCase {

    void invoke(Command command);

    record Command(Long reviewId) {

        public Command(String reviewId) {
            this(IdUtil.from(reviewId));
        }

    }

}
