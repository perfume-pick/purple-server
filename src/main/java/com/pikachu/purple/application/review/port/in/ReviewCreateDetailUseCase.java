package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.bootstrap.review.vo.EvaluationForm;
import java.util.List;

public interface ReviewCreateDetailUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score,
        String content,
        List<EvaluationForm> evaluationForms,
        List<String> moods
    ) {

    }

}
