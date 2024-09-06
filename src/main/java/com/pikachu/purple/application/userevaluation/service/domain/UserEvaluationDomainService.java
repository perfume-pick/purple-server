package com.pikachu.purple.application.userevaluation.service.domain;

import com.pikachu.purple.bootstrap.review.vo.EvaluationForm;
import java.util.List;

public interface UserEvaluationDomainService {

    void create(
        List<Long> userEvaluationIds,
        Long userId,
        Long perfumeId,
        List<EvaluationForm> evaluationForms
    );

}
