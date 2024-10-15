package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.GetReviewsDetailWithEvaluationByUpdatedDateUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetReviewsDetailWithEvaluationByUpdatedDateApplicationService implements
    GetReviewsDetailWithEvaluationByUpdatedDateUseCase {

    private final ReviewDomainService reviewDomainService;

    @Transactional
    @Override
    public Result invoke(Command command) {

        List<Review> reviews = reviewDomainService.findAllWithEvaluation(
            ReviewType.DETAIL,
            command.updatedDate()
        );

        return new Result(reviews);
    }

}
