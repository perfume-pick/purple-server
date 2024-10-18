package com.pikachu.purple.application.user.service.application.useraccord;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.util.RecommendUserAccordsProvider;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsByUserIdUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserByIdUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.application.user.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserAccordApplicationService implements CreateUserAccordUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetStarRatingsByUserIdUseCase getStarRatingsByUserIdUseCase;
    private final RecommendUserAccordsProvider recommendUserAccordsProvider;
    private final UserAccordDomainService userAccordDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(new GetUserByIdUseCase.Command(userId));
        GetStarRatingsByUserIdUseCase.Result starRatings = getStarRatingsByUserIdUseCase.invoke(new GetStarRatingsByUserIdUseCase.Command(userId));

        List<UserAccord> userAccords = recommendUserAccordsProvider.get(
            user.user(),
            starRatings.starRatings()
        );

        userAccordDomainService.createAll(
            user.user().getId(),
            userAccords
        );
    }

}
