package com.pikachu.purple.application.review.service.application.review;

import com.pikachu.purple.application.review.port.in.review.CreateReviewSimpleUseCase;
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
public class CreateReviewSimpleApplicationService implements CreateReviewSimpleUseCase {

    private final ReviewDomainService reviewDomainService;
    private final CreateOrUpdateStarRatingUseCase createOrUpdateStarRatingUseCase;
    private final CreateUserAccordUseCase createUserAccordUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        CreateOrUpdateStarRatingUseCase.Result starRatingResult = createOrUpdateStarRatingUseCase.invoke(
            new CreateOrUpdateStarRatingUseCase.Command(
                command.perfumeId(),
                command.score()
            )
        );

        StarRating starRating = starRatingResult.starRating();
        User user = starRating.getUser();
        Perfume perfume = starRating.getPerfume();

        reviewDomainService.create(
            user.getId(),
            perfume.getId(),
            command.content(),
            ReviewType.SIMPLE
        );

        createUserAccordUseCase.invoke(new CreateUserAccordUseCase.Command(perfume.getId()));
    }

}
