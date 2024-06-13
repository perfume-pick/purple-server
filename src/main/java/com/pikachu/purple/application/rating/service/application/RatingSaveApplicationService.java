package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingSaveUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteSaveUseCase;
import com.pikachu.purple.application.util.IdGenerator;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingSaveApplicationService implements RatingSaveUseCase {

    private final RatingDomainService ratingDomainService;
    private final UserPreferenceNoteSaveUseCase userPreferenceNoteSaveUseCase;

    @Transactional
    @Override
    public void invoke(Command command) {
        List<Long> ratingIdList = IntStream.range(0, command.ratingValueList().size())
            .mapToObj(i -> IdGenerator.generate())
            .collect(Collectors.toList());

        ratingDomainService.create(
            ratingIdList,
            getCurrentUserAuthentication().userId(),
            command.ratingValueList()
        );

        userPreferenceNoteSaveUseCase.invoke();
    }

}
