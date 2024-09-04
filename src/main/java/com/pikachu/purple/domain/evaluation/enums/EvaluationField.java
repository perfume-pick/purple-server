package com.pikachu.purple.domain.evaluation.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationField {
    LONGEVITY(
        "EF001",
        "지속력",
        Arrays.asList(
            EvaluationOption.LONGEVITY_VERY_WEEK,
            EvaluationOption.LONGEVITY_WEEK,
            EvaluationOption.LONGEVITY_MODERATE,
            EvaluationOption.LONGEVITY_LONG_LASTING,
            EvaluationOption.LONGEVITY_ETERNAL
        )
    ),
    SILLAGE(
        "EF002",
        "시야주",
        Arrays.asList(
            EvaluationOption.SILLAGE_INTIMATE,
            EvaluationOption.SILLAGE_MODERATE,
            EvaluationOption.SILLAGE_STRONG,
            EvaluationOption.SILLAGE_ENORMOUS
        )
    ),
    SEASON_TIME(
        "EF003",
        "계절감/시간",
        Arrays.asList(
            EvaluationOption.SEASON_TIME_SPRING,
            EvaluationOption.SEASON_TIME_SUMMER,
            EvaluationOption.SEASON_TIME_FALL,
            EvaluationOption.SEASON_TIME_WINTER,
            EvaluationOption.SEASON_TIME_DAY,
            EvaluationOption.SEASON_TIME_NIGHT
        )
    ),
    GENDER(
        "EF004",
        "성별",
        Arrays.asList(
            EvaluationOption.GENDER_MALE,
            EvaluationOption.GENDER_MORE_MALE,
            EvaluationOption.GENDER_UNISEX,
            EvaluationOption.GENDER_MORE_FEMALE,
            EvaluationOption.GENDER_FEMALE
        )
    );

    private final String code;
    private final String name;
    private final List<EvaluationOption> evaluationOptions;

    private final static Map<String, String> CODE_MAP = Collections.unmodifiableMap(
        Stream.of(values())
            .collect(Collectors.toMap(EvaluationField::getCode, EvaluationField::name))
    );

    public static EvaluationField of(String code) {
        return EvaluationField.valueOf(CODE_MAP.get(code));
    }

}
