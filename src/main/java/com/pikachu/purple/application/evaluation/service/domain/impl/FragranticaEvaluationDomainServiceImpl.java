package com.pikachu.purple.application.evaluation.service.domain.impl;

import com.pikachu.purple.application.evaluation.port.out.FragranticaEvaluationRepository;
import com.pikachu.purple.application.evaluation.service.domain.FragranticaEvaluationDomainService;
import com.pikachu.purple.domain.evaluation.FragranticaEvaluation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FragranticaEvaluationDomainServiceImpl implements FragranticaEvaluationDomainService {

    private final FragranticaEvaluationRepository fragranticaEvaluationRepository;

    @Override
    public List<FragranticaEvaluation> findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
        Long perfumeId,
        String fieldCode,
        int maxSize
    ) {
        return fragranticaEvaluationRepository.findAllByPerfumeIdAndFieldCodeOrderByVotesDesc(
            perfumeId,
            fieldCode,
            maxSize
        );
    }

}
