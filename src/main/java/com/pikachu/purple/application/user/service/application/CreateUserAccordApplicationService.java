package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.util.RecommendUserAccordsProvider;
import com.pikachu.purple.application.rating.port.in.GetRatingsByUserIdUseCase;
import com.pikachu.purple.application.user.port.in.CreateUserAccordUseCase;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import com.pikachu.purple.application.useraccrod.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserAccordApplicationService implements CreateUserAccordUseCase {

    private final GetRatingsByUserIdUseCase getRatingsByUserIdUseCase;
    private final RecommendUserAccordsProvider recommendUserAccordsProvider;
    private final UserAccordDomainService userAccordDomainService;
    private final GetUserByIdUseCase getUserByIdUseCase;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(new GetUserByIdUseCase.Command(userId));

        List<StarRating> starRatings = getRatingsByUserIdUseCase.invoke(userId);
        List<UserAccord> userAccords = recommendUserAccordsProvider.getTopThreeUserAccords(
            user.user(),
            starRatings
        );

        userAccordDomainService.createAll(userAccords);
    }

}
