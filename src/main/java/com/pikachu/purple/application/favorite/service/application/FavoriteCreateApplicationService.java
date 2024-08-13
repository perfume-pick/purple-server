package com.pikachu.purple.application.favorite.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.favorite.port.in.FavoriteCreateUseCase;
import com.pikachu.purple.application.favorite.service.domain.FavoriteDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteCreateApplicationService implements FavoriteCreateUseCase {

    private final FavoriteDomainService favoriteDomainService;

    @Transactional
    @Override
    public void invoke(List<Long> perfumeIds) {
        Long userId = getCurrentUserAuthentication().userId();

        List<Long> favoriteIds = IntStream.range(0, perfumeIds.size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        favoriteDomainService.create(
            favoriteIds,
            userId,
            perfumeIds
        );


    }

}
