package com.pikachu.purple.application.user.service.application.useraccord;

import com.pikachu.purple.application.perfume.util.UserAccordRecommender;
import com.pikachu.purple.application.review.port.in.starrating.GetStarRatingUseCase;
import com.pikachu.purple.application.user.port.in.user.GetUserUseCase;
import com.pikachu.purple.application.user.port.in.useraccord.CreateUserAccordUseCase;
import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateUserAccordService implements CreateUserAccordUseCase {

    private final GetUserUseCase getUserUseCase;
    private final UserAccordRecommender userAccordRecommender;
    private final GetStarRatingUseCase getStarRatingUseCase;
    private final UserAccordRepository userAccordRepository;

    @Override
    public void createAll(
        Long userId,
        Long perfumeId
    ) {
        GetUserUseCase.Result user = getUserUseCase.find(userId);
        GetStarRatingUseCase.Result starRating = getStarRatingUseCase.find(
            userId,
            perfumeId
        );
        List<UserAccord> userAccords = userAccordRecommender.recommend(
            user.user(),
            starRating.starRating()
        );

        userAccordRepository.createAll(
            user.user().getId(),
            userAccords
        );
    }

}
