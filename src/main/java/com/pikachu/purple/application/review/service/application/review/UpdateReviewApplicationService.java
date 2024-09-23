package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateReviewApplicationService implements UpdateReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Review review = reviewDomainService.updateContent(
            command.reviewId(),
            command.content()
        );

        GetStarRatingUseCase.Result starRatingResult = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                review.getPerfume().getId()
            )
        );

        StarRating previousStarRating = starRatingResult.starRating();
        updateStarRatingUseCase.invoke(
            new UpdateStarRatingUseCase.Command(
                previousStarRating.getPerfume().getId(),
                previousStarRating.getScore(),
                command.score()
            )
        );

    }

}
