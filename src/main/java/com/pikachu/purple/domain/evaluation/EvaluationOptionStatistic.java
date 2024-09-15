package com.pikachu.purple.domain.evaluation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationOptionStatistic extends EvaluationOption {

    private int votes;

}
