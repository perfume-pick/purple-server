package com.pikachu.purple.domain.evaluation.enums;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EvaluationOptionType {

    LONGEVITY_VERY_WEEK("EO101", "매우 약해요"),
    LONGEVITY_WEEK("EO102", "약해요"),
    LONGEVITY_MODERATE("EO103", "보통이에요"),
    LONGEVITY_LONG_LASTING("EO104", "오래가요"),
    LONGEVITY_ETERNAL("EO105", "매우 오래가요"),

    SILLAGE_INTIMATE("EO201", "향 여운이 약해요"),
    SILLAGE_MODERATE("EO202", "보통이에요"),
    SILLAGE_STRONG("EO203", "향 여운이 강해요"),
    SILLAGE_ENORMOUS("EO204", "향 여운이 매우 강해요"),

    SEASON_TIME_SPRING("EO301", "봄"),
    SEASON_TIME_SUMMER("EO302", "여름"),
    SEASON_TIME_FALL("EO303", "가을"),
    SEASON_TIME_WINTER("EO304", "겨울"),
    SEASON_TIME_DAY("EO305", "낮"),
    SEASON_TIME_NIGHT("EO306", "밤"),

    GENDER_MALE("EO401", "남성적이에요"),
    GENDER_MORE_MALE("EO402", "남성에 가까워요"),
    GENDER_UNISEX("EO403", "중성적이에요"),
    GENDER_MORE_FEMALE("EO404", "여성에 가까워요"),
    GENDER_FEMALE("EO405", "여성적이에요");

    private final String code;
    private final String name;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
        Stream.of(values())
            .collect(Collectors.toMap(EvaluationOptionType::getCode, EvaluationOptionType::name))
    );

    public static EvaluationOptionType of(String code) {
        return EvaluationOptionType.valueOf(CODE_MAP.get(code));
    }

}
