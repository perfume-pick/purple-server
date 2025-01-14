package com.pikachu.purple.application.review.port.in.review;

import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import java.util.List;

public interface CreateReviewDetailUseCase {

    void invoke(
        Long perfumeId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames
    );

}
