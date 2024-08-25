package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

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
        Long userId = getCurrentUserAuthentication().userId();

        Review review = reviewDomainService.getByIdAndUserId(
            reviewId,
            userId
        );

        reviewDomainService.delete(review);

        ratingDeleteUseCase.invoke(review.getRatingId());
    }

}
