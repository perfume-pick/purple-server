package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.RatingSaveUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingSaveService implements RatingSaveUseCase {

    private final RatingDomainService ratingDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        ratingDomainService.create(
            IdGenerator.generate(),
            command.userId(),
            command.ratingValueList()
        );
    }

}
