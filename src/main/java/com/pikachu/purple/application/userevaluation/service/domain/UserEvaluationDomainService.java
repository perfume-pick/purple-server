package com.pikachu.purple.application.userevaluation.service.domain;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import java.util.List;

public interface UserEvaluationDomainService {

    void create(
        Long userId,
        Long perfumeId,
        List<EvaluationFieldVO> evaluationFieldVOs
    );

}
