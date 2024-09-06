package com.pikachu.purple.application.userevaluation.port.in;

import com.pikachu.purple.bootstrap.review.vo.EvaluationForm;
import java.util.List;

public interface UserEvaluationCreateUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        List<EvaluationForm> evaluationForms
    ) {

    }

}
