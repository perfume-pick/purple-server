package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingCreateOnboardingUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.userpreferencenote.port.in.UserPreferenceNoteCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RatingCreateOnboardingApplicationService implements RatingCreateOnboardingUseCase {

    private final RatingDomainService ratingDomainService;
    private final UserPreferenceNoteCreateUseCase userPreferenceNoteCreateUseCase;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        ratingDomainService.createOnboarding(
            userId,
            command.ratingInfos()
        );

        userPreferenceNoteCreateUseCase.invoke();
    }

}
