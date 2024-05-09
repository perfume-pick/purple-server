package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeBrandJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeBrandJpaRepository extends JpaRepository<PerfumeBrandJpaEntity, String> {

}
