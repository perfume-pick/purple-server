package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.CreateReviewUseCase;
import com.pikachu.purple.application.review.port.in.review.CreateSimpleReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateSimpleReviewService implements CreateSimpleReviewUseCase {

    private final CreateReviewUseCase createReviewUseCase;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    @Transactional
    @Override
    public void create(
        Long userId,
        Long perfumeId,
        int score,
        String content
    ) {
        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            userId,
            perfumeId,
            score
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        createReviewUseCase.create(
            user.getId(),
            perfume.getId(),
            content,
            ReviewType.SIMPLE
        );

        createUserAccordUseCase.createAll(
            userId,
            perfume.getId()
        );
    }

}
