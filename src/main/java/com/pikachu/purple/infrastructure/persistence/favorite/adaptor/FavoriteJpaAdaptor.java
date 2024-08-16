package com.pikachu.purple.infrastructure.persistence.favorite.adaptor;

import com.pikachu.purple.application.favorite.port.out.FavoriteRepository;
import com.pikachu.purple.domain.favorite.Favorite;
import com.pikachu.purple.infrastructure.persistence.favorite.entity.FavoriteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.favorite.repository.FavoriteJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FavoriteJpaAdaptor implements FavoriteRepository {

    private final FavoriteJpaRepository favoriteJpaRepository;

    @Override
    public void create(List<Favorite> favorites) {
        List<FavoriteJpaEntity> favoriteJpaEntities = favorites.stream()
            .map(FavoriteJpaEntity::toJpaEntity)
            .toList();

        favoriteJpaRepository.saveAll(favoriteJpaEntities);
    }

}
