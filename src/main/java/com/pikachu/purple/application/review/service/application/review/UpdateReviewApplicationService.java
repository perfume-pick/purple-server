package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateReviewApplicationService implements UpdateReviewUseCase {

    private final UpdateStarRatingUseCase updateStarRatingUseCase;
    private final ReviewDomainService reviewDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        Review review = reviewDomainService.updateContent(
            command.reviewId(),
            command.content()
        );

        updateStarRatingUseCase.invoke(
            new UpdateStarRatingUseCase.Command(
                review.getPerfume().getId(),
                command.score()
            )
        );

    }

}
