package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationOption {

    private EvaluationOptionType type;

}
