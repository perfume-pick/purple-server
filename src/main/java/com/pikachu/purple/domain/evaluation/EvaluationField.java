package com.pikachu.purple.domain.evaluation;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationField<T> {

    private EvaluationFieldType type;
    private List<T> options;

}
