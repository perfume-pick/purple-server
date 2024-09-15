package com.pikachu.purple.domain.statistic;

import com.pikachu.purple.domain.evaluation.EvaluationField;
import com.pikachu.purple.domain.evaluation.EvaluationOptionStatistic;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationStatistic {

    private List<EvaluationField<EvaluationOptionStatistic>> fields;

}
