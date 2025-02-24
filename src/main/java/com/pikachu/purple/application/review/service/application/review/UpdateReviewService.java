package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdateReviewService implements UpdateReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Override
    public void invoke(
        Long reviewId,
        Long perfumeId,
        ReviewType reviewType,
        String content,
        int score
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        reviewDomainService.update(
            reviewId,
            content,
            reviewType
        );

        GetStarRatingUseCase.Result starRatingResult = getStarRatingUseCase.find(
            userId,
            perfumeId
        );

        StarRating previousStarRating = starRatingResult.starRating();

        updateStarRatingUseCase.invoke(
            previousStarRating.getPerfume().getId(),
            previousStarRating.getScore(),
            score
        );
    }

}
