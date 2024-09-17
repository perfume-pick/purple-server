package com.pikachu.purple.application.rating.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.DeleteStarRatingUseCase;
import com.pikachu.purple.application.rating.service.domain.StarRatingDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStarRatingApplicationService implements DeleteStarRatingUseCase {

    private final StarRatingDomainService starRatingDomainService;

    @Override
    public void invoke(Long perfumeId) {
        Long userId = getCurrentUserAuthentication().userId();

        starRatingDomainService.deleteByUserIdAndPerfumeId(
            userId,
            perfumeId
        );
    }

}
