package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewCreateSimpleUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.domain.review.enums.ReviewType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCreateSimpleApplicationService implements ReviewCreateSimpleUseCase {

    private final RatingCreateUseCase ratingCreateUseCase;
    private final ReviewDomainService reviewDomainService;

    @Override
    @Transactional
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        ratingCreateUseCase.invoke(new RatingCreateUseCase.Command(
            command.perfumeId(),
            command.score()
        ));

        reviewDomainService.create(
            command.perfumeId(),
            userId,
            command.content(),
            ReviewType.SIMPLE
        );
    }

}
