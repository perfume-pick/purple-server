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
public enum EvaluationFieldType {
    LONGEVITY(
        "EF001",
        "지속력",
        Arrays.asList(
            EvaluationOptionType.LONGEVITY_VERY_WEEK,
            EvaluationOptionType.LONGEVITY_WEEK,
            EvaluationOptionType.LONGEVITY_MODERATE,
            EvaluationOptionType.LONGEVITY_LONG_LASTING,
            EvaluationOptionType.LONGEVITY_ETERNAL
        )
    ),
    SILLAGE(
        "EF002",
        "시야주",
        Arrays.asList(
            EvaluationOptionType.SILLAGE_INTIMATE,
            EvaluationOptionType.SILLAGE_MODERATE,
            EvaluationOptionType.SILLAGE_STRONG,
            EvaluationOptionType.SILLAGE_ENORMOUS
        )
    ),
    SEASON_TIME(
        "EF003",
        "계절감/시간",
        Arrays.asList(
            EvaluationOptionType.SEASON_TIME_SPRING,
            EvaluationOptionType.SEASON_TIME_SUMMER,
            EvaluationOptionType.SEASON_TIME_FALL,
            EvaluationOptionType.SEASON_TIME_WINTER,
            EvaluationOptionType.SEASON_TIME_DAY,
            EvaluationOptionType.SEASON_TIME_NIGHT
        )
    ),
    GENDER(
        "EF004",
        "성별",
        Arrays.asList(
            EvaluationOptionType.GENDER_MALE,
            EvaluationOptionType.GENDER_MORE_MALE,
            EvaluationOptionType.GENDER_UNISEX,
            EvaluationOptionType.GENDER_MORE_FEMALE,
            EvaluationOptionType.GENDER_FEMALE
        )
    );

    private final String code;
    private final String name;
    private final List<EvaluationOptionType> evaluationOptionTypes;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
        Stream.of(values())
            .collect(Collectors.toMap(EvaluationFieldType::getCode, EvaluationFieldType::name))
    );

    public static EvaluationFieldType of(String code) {
        return EvaluationFieldType.valueOf(CODE_MAP.get(code));
    }

    public boolean is(EvaluationFieldType evaluationFieldType) {
        return this == evaluationFieldType;
    }

}
