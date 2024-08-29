package com.pikachu.purple.infrastructure.persistence.mainAccord.repository;

import com.pikachu.purple.infrastructure.persistence.mainAccord.entity.MainAccordJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainAccordJpaRepository extends JpaRepository<MainAccordJpaEntity, Long> {

    List<MainAccordJpaEntity> findAllByPerfumeId(Long perfumeId, Limit limit);

}