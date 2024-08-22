package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingCreateApplicationService implements RatingCreateUseCase {

    private final RatingDomainService ratingDomainService;

    @Override
    @Transactional
    public void createOnboarding(OnboardingCommand command) {
        List<Long> ratingIds = IntStream.range(0, command.ratingValues().size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        Long userId = getCurrentUserAuthentication().userId();

        ratingDomainService.createOnboarding(
            ratingIds,
            userId,
            command.reviewIds(),
            command.ratingValues()
        );
    }

    @Override
    @Transactional
    public void create(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        ratingDomainService.create(
            IdGenerator.generate(),
            userId,
            command.reviewId(),
            command.score()
        );
    }

}
