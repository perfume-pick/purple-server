package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.UpdateRatingUseCase;
import com.pikachu.purple.application.review.port.in.ReviewUpdateUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewUpdateApplicationService implements ReviewUpdateUseCase {

    private final UpdateRatingUseCase updateRatingUseCase;
    private final ReviewDomainService reviewDomainService;


    @Override
    @Transactional
    public void invoke(Command command) {
        reviewDomainService.updateContent(
            command.reviewId(),
            command.content()
        );

        Review review = reviewDomainService.findWithPerfumeById(command.reviewId());

        updateRatingUseCase.invoke(
            new UpdateRatingUseCase.Command(
                review.getPerfume().getId(),
                command.score()
            )
        );

    }

}
