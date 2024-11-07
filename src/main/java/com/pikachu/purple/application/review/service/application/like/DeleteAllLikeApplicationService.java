package com.pikachu.purple.application.review.service.application.like;

import com.pikachu.purple.application.review.port.in.like.DeleteAllLikeUseCase;
import com.pikachu.purple.application.review.service.domain.LikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAllLikeApplicationService implements DeleteAllLikeUseCase {

    private final LikeDomainService likeDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {

        likeDomainService.deleteAll(command.reviewId());

    }

}
