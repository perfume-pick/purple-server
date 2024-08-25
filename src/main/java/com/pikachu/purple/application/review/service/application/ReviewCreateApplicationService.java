package com.pikachu.purple.application.review.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.rating.port.in.RatingCreateUseCase;
import com.pikachu.purple.application.review.port.in.ReviewCreateUseCase;
import com.pikachu.purple.application.review.service.domain.ReviewDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCreateApplicationService implements ReviewCreateUseCase {

    private final RatingCreateUseCase ratingCreateUseCase;
    private final ReviewDomainService reviewDomainService;

    @Override
    @Transactional
    public void create(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        Long ratingId = ratingCreateUseCase.create(new RatingCreateUseCase.Command(
            command.perfumeId(),
            command.score()
        ));

        reviewDomainService.create(
            IdGenerator.generate(),
            command.perfumeId(),
            userId,
            ratingId,
            command.content()
        );
    }

}
