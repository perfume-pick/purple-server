package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.application.util.IdUtil;

public interface UpdateReviewUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        int score,
        String content
    ) {

        public Command(
            String reviewId,
            int score,
            String content
        ) {
            this(
                IdUtil.from(reviewId),
                score,
                content
            );
        }

    }

}
