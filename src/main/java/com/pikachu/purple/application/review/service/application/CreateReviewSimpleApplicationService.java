package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.GetPerfumeByIdUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.review.port.in.CreateReviewSimpleUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReviewSimpleApplicationService implements CreateReviewSimpleUseCase {

    private final GetPerfumeByIdUseCase getPerfumeByIdUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final ReviewDomainService reviewDomainService;
    private final StarRatingDomainService starRatingDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        StarRating starRating = starRatingDomainService.create(
            userId,
            command.perfumeId(),
            command.score()
        );

        reviewDomainService.create(
            starRating.getUser().getId(),
            starRating.getPerfume().getId(),
            command.content(),
            ReviewType.SIMPLE
        );
    }

}
