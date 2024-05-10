package com.pikachu.purple.application.rating.service.application;

import com.pikachu.purple.application.rating.port.in.RatingSaveUseCase;
import com.pikachu.purple.application.rating.service.domain.RatingDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingSaveService implements RatingSaveUseCase {

    private final RatingDomainService ratingDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        List<Long> ratingIdList = new ArrayList<>();
        for(int i=0; i<command.ratingValueList().size(); i++){
            ratingIdList.add(IdGenerator.generate());
        }

        ratingDomainService.create(
            ratingIdList,
            command.userId(),
            command.ratingValueList()
        );
    }

}
