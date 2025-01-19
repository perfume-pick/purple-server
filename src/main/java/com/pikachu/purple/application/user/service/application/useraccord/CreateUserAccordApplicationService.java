package com.pikachu.purple.application.user.service.application.useraccord;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.util.UserAccordRecommender;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserByIdUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateUserAccordApplicationService implements CreateUserAccordUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UserAccordDomainService userAccordDomainService;
    private final UserAccordRecommender userAccordRecommender;
    private final GetStarRatingUseCase getStarRatingUseCase;

    @Override
    public void invoke(Long perfumeId) {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(userId);
        GetStarRatingUseCase.Result starRating = getStarRatingUseCase.invoke(
            userId,
            perfumeId
        );
        List<UserAccord> userAccords = userAccordRecommender.recommend(
            user.user(),
            starRating.starRating()
        );

        userAccordDomainService.createAll(
            user.user().getId(),
            userAccords
        );
    }

}
