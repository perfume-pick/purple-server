package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.rating.port.in.RatingDeleteUseCase;
import com.pikachu.purple.application.review.port.in.ReviewDeleteUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewDeleteApplicationService implements ReviewDeleteUseCase {

    private final ReviewDomainService reviewDomainService;
    private final RatingDeleteUseCase ratingDeleteUseCase;

    @Override
    @Transactional
    public void invoke(Long reviewId) {
        Review review = reviewDomainService.findById(reviewId);

        reviewDomainService.delete(review);

        ratingDeleteUseCase.invoke(review.getPerfumeId());
    }

}
