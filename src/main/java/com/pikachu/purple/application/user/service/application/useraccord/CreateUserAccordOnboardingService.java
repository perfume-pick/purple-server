package com.pikachu.purple.application.user.service.application.useraccord;

import com.pikachu.purple.application.perfume.util.UserAccordRecommender;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingsUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordOnboardingUseCase;
import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class CreateUserAccordOnboardingService implements
    CreateUserAccordOnboardingUseCase {

    private final GetUserUseCase getUserUseCase;
    private final GetStarRatingsUseCase getStarRatingsUseCase;
    private final UserAccordRecommender userAccordRecommender;
    private final UserAccordRepository userAccordRepository;

    @Transactional
    @Override
    public void createAll(Long userId) {
        GetUserUseCase.Result user = getUserUseCase.findByUserId(userId);
        GetStarRatingsUseCase.Result starRatings = getStarRatingsUseCase.findAllByUserIdWithPerfume(userId);

        List<UserAccord> userAccords = userAccordRecommender.recommend(
            user.user(),
            starRatings.starRatings()
        );

        userAccordRepository.createAll(
            user.user().getId(),
            userAccords
        );
    }

}
