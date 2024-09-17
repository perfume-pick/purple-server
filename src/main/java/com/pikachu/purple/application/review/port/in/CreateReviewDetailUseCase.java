package com.pikachu.purple.application.review.port.in;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import java.util.List;

public interface CreateReviewDetailUseCase {

    void invoke(Command command);

    record Command(
        Long perfumeId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {

    }

}
