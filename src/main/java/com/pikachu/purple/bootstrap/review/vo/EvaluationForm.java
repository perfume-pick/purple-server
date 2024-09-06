package com.pikachu.purple.bootstrap.review.vo;

import java.util.List;

public record EvaluationForm(
    String fieldCode,
    List<String> optionCodes
) {

}
