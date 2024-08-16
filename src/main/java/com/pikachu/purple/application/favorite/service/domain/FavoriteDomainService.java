package com.pikachu.purple.application.favorite.service.domain;

import java.util.List;

public interface FavoriteDomainService {

    void create(
        List<Long> favoriteIds,
        Long userId,
        List<Long> perfumeIds
    );

}
