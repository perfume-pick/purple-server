package com.pikachu.purple.application.review.service.application.review;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.review.CreateSimpleReviewUseCase;
import com.pikachu.purple.application.review.port.in.starrating.CreateOrUpdateStarRatingUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
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

    private final ReviewDomainService reviewDomainService;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    @Transactional
    @Override
    public void invoke(
        Long perfumeId,
        int score,
        String content
    ) {
        Long userId = getCurrentUserAuthentication().userId();

        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            userId,
            perfumeId,
            score
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            content,
            ReviewType.SIMPLE
        );

        createUserAccordUseCase.invoke(perfume.getId());
    }

}
