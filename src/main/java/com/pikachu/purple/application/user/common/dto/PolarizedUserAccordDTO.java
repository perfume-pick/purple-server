package com.pikachu.purple.application.user.common.dto;

import java.util.List;

public record PolarizedUserAccordDTO(
    List<AccordInfo> preferredAccord,
    List<AccordInfo> dislikedAccord
) {

    public record AccordInfo(
        String accordName,
        String accordKoreanName,
        int count,
        int percentage
    ) {

        public static AccordInfo of(
            String accordName,
            String accordKoreanName,
            int count,
            int percentage
        ) {
            return new AccordInfo(
                accordName,
                accordKoreanName,
                count,
                percentage
            );
        }

    }

}
