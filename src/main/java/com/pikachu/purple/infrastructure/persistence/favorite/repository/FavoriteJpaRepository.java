package com.pikachu.purple.infrastructure.persistence.favorite.repository;

import com.pikachu.purple.infrastructure.persistence.favorite.entity.FavoriteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteJpaRepository extends JpaRepository<FavoriteJpaEntity, Long> {

}
