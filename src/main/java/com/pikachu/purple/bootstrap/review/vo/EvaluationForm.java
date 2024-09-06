package com.pikachu.purple.bootstrap.review.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record EvaluationForm(
    @Schema(description = "지속력, 시야주, 계절감/시간, 성별")
    String fieldCode,
    @Schema(description = "fieldCode에 해당하는 값")
    List<String> optionCodes
) {

}
