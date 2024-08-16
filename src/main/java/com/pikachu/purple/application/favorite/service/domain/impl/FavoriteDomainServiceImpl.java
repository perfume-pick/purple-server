package com.pikachu.purple.application.favorite.service.domain.impl;

import com.pikachu.purple.application.favorite.port.out.FavoriteRepository;
import com.pikachu.purple.application.favorite.service.domain.FavoriteDomainService;
import com.pikachu.purple.domain.favorite.Favorite;
import com.pikachu.purple.infrastructure.persistence.common.EntityStatus;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteDomainServiceImpl implements FavoriteDomainService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public void create(
        List<Long> favoriteIds,
        Long userId,
        List<Long> perfumeIds
    ) {
        List<Favorite> favorites = IntStream.range(0, favoriteIds.size())
            .mapToObj(i -> Favorite.builder()
                .favoriteId(favoriteIds.get(i))
                .userId(userId)
                .perfumeId(perfumeIds.get(i))
                .entityStatus(EntityStatus.ACTIVE)
                .build())
            .toList();

        favoriteRepository.create(favorites);
    }

}
