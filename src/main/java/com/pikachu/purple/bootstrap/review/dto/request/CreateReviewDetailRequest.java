package com.pikachu.purple.bootstrap.review.dto.request;

import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.bootstrap.review.vo.EvaluationFieldVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.hibernate.validator.constraints.Range;

public record CreateReviewDetailRequest(
    Long perfumeId,
    @Range(min = 1, max = 5)
    int score,
    @Size(min = 10, max = 300, message = "최소 10자 ~ 최대 300자 입력할 수 있습니다.")
    String content,
    List<EvaluationFieldVO> evaluationFieldVOs,
    @Schema(description = "향수에 어울리는 분위기")
    List<String> moodNames
) {

    public CreateReviewDetailRequest(
        String perfumeId,
        int score,
        String content,
        List<EvaluationFieldVO> evaluationFieldVOs,
        List<String> moodNames)
    {
        this(IdUtil.from(perfumeId), score, content, evaluationFieldVOs, moodNames);
    }

}
