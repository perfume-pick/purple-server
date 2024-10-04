package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.StarRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateReviewApplicationService implements UpdateReviewUseCase {

    private final ReviewDomainService reviewDomainService;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        reviewDomainService.update(
            command.reviewId(),
            command.content(),
            command.reviewType()
        );

        GetStarRatingUseCase.Result starRatingResult = getStarRatingUseCase.invoke(
            new GetStarRatingUseCase.Command(
                userId,
                command.perfumeId()
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
