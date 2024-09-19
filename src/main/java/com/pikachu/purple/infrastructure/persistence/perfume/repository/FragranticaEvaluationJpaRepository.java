package com.pikachu.purple.infrastructure.persistence.perfume.repository;

import com.pikachu.purple.infrastructure.persistence.perfume.entity.FragranticaEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.PerfumeAccordJpaEntity;
import java.util.List;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FragranticaEvaluationJpaRepository extends
    JpaRepository<FragranticaEvaluationJpaEntity, Long> {

    @Query("select fe "
        + "from FragranticaEvaluationJpaEntity fe "
        + "where fe.perfumeJpaEntity.id = :perfumeId "
        + "order by fe.fieldCode, fe.votes DESC, fe.optionCode")
    List<FragranticaEvaluationJpaEntity> findByPerfumeIdOrderByVotesDesc(Long perfumeId);

}
