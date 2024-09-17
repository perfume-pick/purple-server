package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.GetPerfumeByIdUseCase;
import com.pikachu.purple.application.rating.port.in.CreateStarRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import com.pikachu.purple.application.user.port.in.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStarRatingApplicationService implements CreateStarRatingUseCase {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetPerfumeByIdUseCase getPerfumeByIdUseCase;
    private final StarRatingDomainService starRatingDomainService;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        GetUserByIdUseCase.Result user = getUserByIdUseCase.invoke(new GetUserByIdUseCase.Command(userId));
        GetPerfumeByIdUseCase.Result perfume = getPerfumeByIdUseCase.invoke(new GetPerfumeByIdUseCase.Command(command.perfumeId()));

        starRatingDomainService.create(
            user.user(),
            perfume.perfume(),
            command.score()
        );
    }

}