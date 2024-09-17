package com.pikachu.purple.application.review.service.application;

import com.pikachu.purple.application.rating.port.in.DeleteStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.DeleteReviewUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteReviewApplicationService implements DeleteReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final DeleteStarRatingUseCase deleteStarRatingUseCase;

    @Override
    @Transactional
    public void invoke(Long reviewId) {

        Review review = reviewDomainService.deleteById(reviewId);

        deleteStarRatingUseCase.invoke(review.getPerfume().getId());
    }

}
