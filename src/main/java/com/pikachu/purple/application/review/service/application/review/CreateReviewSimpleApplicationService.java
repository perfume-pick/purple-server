package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.port.in.review.UpdateReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateStarRatingUseCase;
import com.pikachu.purple.application.review.port.in.starrating.UpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewSimpleApplicationService implements CreateReviewSimpleUseCase {

    private final ReviewDomainService reviewDomainService;
    private final CreateStarRatingUseCase createStarRatingUseCase;
    private final UpdateStarRatingUseCase updateStarRatingUseCase;

    @Transactional
    @Override

    public void invoke(Command command) {

        CreateStarRatingUseCase.Result result = createStarRatingUseCase.invoke(
            new CreateStarRatingUseCase.Command(
                command.perfumeId(),
                command.score()
            )
        );

        User user = result.starRating().getUser();
        Perfume perfume = result.starRating().getPerfume();

        reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            command.content(),
            ReviewType.SIMPLE
        );

    }

}
