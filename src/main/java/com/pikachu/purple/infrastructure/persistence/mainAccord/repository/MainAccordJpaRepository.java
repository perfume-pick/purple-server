package com.pikachu.purple.infrastructure.persistence.mainAccord.repository;

import com.pikachu.purple.infrastructure.persistence.mainAccord.entity.MainAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeJpaEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainAccordJpaRepository extends JpaRepository<MainAccordJpaEntity, Long> {

    List<MainAccordJpaEntity> findAllByPerfumeId(Long perfumeId, Limit limit);

}