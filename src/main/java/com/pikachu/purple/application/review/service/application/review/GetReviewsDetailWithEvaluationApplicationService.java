package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.GetReviewsDetailWithEvaluationUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetReviewsDetailWithEvaluationApplicationService implements
    GetReviewsDetailWithEvaluationUseCase {

    private final ReviewDomainService reviewDomainService;

    @Transactional
    @Override
    public Result invoke() {
        List<Review> reviews = reviewDomainService.findAllWithEvaluation(ReviewType.DETAIL);

        return new Result(reviews);
    }

}
