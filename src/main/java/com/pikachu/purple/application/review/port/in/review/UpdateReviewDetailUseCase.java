package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import java.util.List;

public interface UpdateReviewDetailUseCase {

    void invoke(Command command);

    record Command(
        Long reviewId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    ) {}

}
