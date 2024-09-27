package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.DeleteReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.DeleteStarRatingUseCase;
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

    @Transactional
    @Override
    public void invoke(Command command) {

        Review review = reviewDomainService.findById(command.reviewId());

        deleteStarRatingUseCase.invoke(
            new DeleteStarRatingUseCase.Command(review.getStarRating().getId())
        );

        reviewDomainService.deleteById(command.reviewId());
    }

}
