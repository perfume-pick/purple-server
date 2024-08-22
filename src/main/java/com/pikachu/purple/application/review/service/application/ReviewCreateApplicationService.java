package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.favorite.port.in.FavoriteCreateUseCase;
import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewCreateUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteCreateUseCase;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.review.vo.RatingValue;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCreateApplicationService implements ReviewCreateUseCase {

    private final RatingCreateUseCase ratingCreateUseCase;
    private final UserPreferenceNoteCreateUseCase userPreferenceNoteCreateUseCase;
    private final FavoriteCreateUseCase favoriteCreateUseCase;
    private final ReviewDomainService reviewDomainService;

    @Override
    @Transactional
    public void createOnboarding(OnboardingCommand command) {
        Long userId = getCurrentUserAuthentication().userId();

        List<Long> reviewIds = IntStream.range(0, command.ratingValues().size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        List<Long> perfumeIds = command.ratingValues().stream()
            .map(RatingValue::perfumeId)
            .toList();

        reviewDomainService.createOnboarding(
            reviewIds,
            perfumeIds,
            userId
        );

        ratingCreateUseCase.createOnboarding(
            new RatingCreateUseCase.OnboardingCommand(
                reviewIds,
                command.ratingValues()
            )
        );

        userPreferenceNoteCreateUseCase.invoke();
        favoriteCreateUseCase.invoke(perfumeIds);
    }

    @Override
    @Transactional
    public void create(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Long reviewId = reviewDomainService.create(
            IdGenerator.generate(),
            command.perfumeId(),
            userId,
            command.content()
        );

        ratingCreateUseCase.create(new RatingCreateUseCase.Command(
            reviewId,
            command.score()
        ));


    }

}
