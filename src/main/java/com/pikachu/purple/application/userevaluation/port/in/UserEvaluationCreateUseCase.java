package com.pikachu.purple.application.userevaluation.port.in;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import java.util.List;

public interface UserEvaluationCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        List<EvaluationFieldVO> evaluationFieldVOs
    ) {

    }

}
