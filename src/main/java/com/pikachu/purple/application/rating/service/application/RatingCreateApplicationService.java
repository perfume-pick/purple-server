package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.favorite.port.in.FavoriteCreateUseCase;
import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteCreateUseCase;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.bootstrap.rating.vo.RatingInfo;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingCreateApplicationService implements RatingCreateUseCase {

    private final RatingDomainService ratingDomainService;
    private final UserPreferenceNoteCreateUseCase userPreferenceNoteCreateUseCase;
    private final FavoriteCreateUseCase favoriteCreateUseCase;

    @Override
    @Transactional
    public void createOnboarding(OnboardingCommand command) {
        List<Long> ratingIds = IntStream.range(0, command.ratingInfos().size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        Long userId = getCurrentUserAuthentication().userId();

        ratingDomainService.createOnboarding(
            ratingIds,
            userId,
            command.ratingInfos()
        );

        List<Long> perfumeIds = command.ratingInfos().stream()
            .map(RatingInfo::perfumeId)
            .toList();

        userPreferenceNoteCreateUseCase.invoke();
        favoriteCreateUseCase.invoke(perfumeIds);
    }

    @Override
    @Transactional
    public Rating create(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        return ratingDomainService.create(
            IdGenerator.generate(),
            userId,
            command.perfumeId(),
            command.score()
        );
    }

}
