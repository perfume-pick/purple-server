package com.pikachu.purple.bootstrap.review.vo;

import java.util.List;

public record EvaluationFieldVO(
    String fieldCode,
    List<String> optionCodes
) {

}
