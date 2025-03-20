package com.pikachu.purple.domain.review.enums;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.MoodNotException;

import java.util.EnumSet;
import java.util.List;
import lombok.Getter;

@Getter
public enum Mood {
    SENSUAL("관능적인"),
    CUTE("귀여운"),
    NEAT("깔끔한"),
    MACHO("마초적인"),
    SOPHISTICATED("세련된"),
    SEXY("섹시한"),
    SPORTY("스포티한"),
    ELEGANT("우아한"),
    DIGNIFIED("중후한"),
    PURE("청순한"),
    CASUAL("캐주얼한"),
    COMFORTABLE("편안한");

    private final String koreanName;

    Mood(String koreanName) {
        this.koreanName = koreanName;
    }

    public static List<Mood> transMoodsByKoreanNames(List<String> koreanNames) {
        return koreanNames.stream()
            .map(koreanName -> EnumSet.allOf(Mood.class).stream()
                .filter(mood -> mood.koreanName.equals(koreanName))
                .findAny()
                .orElseThrow(() -> MoodNotException))
            .toList();
    }

}
