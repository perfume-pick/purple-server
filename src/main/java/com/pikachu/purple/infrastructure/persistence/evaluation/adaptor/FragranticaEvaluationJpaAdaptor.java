package com.pikachu.purple.infrastructure.persistence.evaluation.adaptor;

import com.pikachu.purple.application.evaluation.port.out.FragranticaEvaluationRepository;
import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import com.pikachu.purple.infrastructure.persistence.evaluation.entity.FragranticaEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.evaluation.repository.FragranticaEvaluationJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class FragranticaEvaluationJpaAdaptor implements FragranticaEvaluationRepository {

    private final FragranticaEvaluationJpaRepository fragranticaEvaluationJpaRepository;

    @Override
    public List<FragranticaEvaluation> findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
        Long perfumeId,
        String fieldCode,
        int maxSize
    ) {
        List<FragranticaEvaluationJpaEntity> fragranticaEvaluationJpaEntities = fragranticaEvaluationJpaRepository.findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
            perfumeId,
            fieldCode,
            Limit.of(maxSize)
        );

        return fragranticaEvaluationJpaEntities.stream()
            .map(FragranticaEvaluationJpaEntity::toDomain).toList();
    }

}
