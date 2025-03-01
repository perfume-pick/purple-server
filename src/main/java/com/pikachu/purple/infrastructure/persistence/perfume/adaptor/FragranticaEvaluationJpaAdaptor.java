package com.pikachu.purple.infrastructure.persistence.perfume.adaptor;

import com.pikachu.purple.application.perfume.port.out.FragranticaEvaluationRepository;
import com.pikachu.purple.domain.perfume.FragranticaEvaluation;
import com.pikachu.purple.infrastructure.persistence.perfume.entity.FragranticaEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.perfume.repository.FragranticaEvaluationJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FragranticaEvaluationJpaAdaptor implements FragranticaEvaluationRepository {

    private final FragranticaEvaluationJpaRepository fragranticaEvaluationJpaRepository;


    @Override
    public FragranticaEvaluation findByPerfumeIdOrderByVotesDesc(Long perfumeId) {
        List<FragranticaEvaluationJpaEntity> fragranticaEvaluationJpaEntities =
            fragranticaEvaluationJpaRepository.findByPerfumeIdOrderByFieldCodeAscVotesDescOptionCodeAsc(
                perfumeId);

        return FragranticaEvaluationJpaEntity.toDomain(fragranticaEvaluationJpaEntities);
    }
}
