package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.GetDetailedReviewsUseCase;
import com.pikachu.purple.application.review.port.out.ReviewRepository;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.enums.ReviewType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class GetDetailedReviewsService implements
    GetDetailedReviewsUseCase {

    private final ReviewRepository reviewRepository;

    @Transactional
    @Override
    public Result findAll() {
        List<Review> reviews = reviewRepository.findAllWithEvaluation(ReviewType.DETAIL);

        return new Result(reviews);
    }

}
