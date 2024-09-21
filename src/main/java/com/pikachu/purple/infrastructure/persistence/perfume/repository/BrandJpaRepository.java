package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.BrandJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandJpaRepository extends JpaRepository<BrandJpaEntity, String> {}
