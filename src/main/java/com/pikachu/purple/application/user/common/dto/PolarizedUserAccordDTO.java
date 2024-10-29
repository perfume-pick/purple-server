package com.pikachu.purple.application.user.common.dto;

import java.util.List;

public record PolarizedUserAccordDTO(
    List<AccordInfo> preferredAccord,
    List<AccordInfo> dislikedAccord
) {

    public record AccordInfo(
        String accordName,
        int count,
        int percentage
    ) {

        public static AccordInfo of(
            String accordName,
            int count,
            int percentage
        ) {
            return new AccordInfo(
                accordName,
                count,
                percentage
            );
        }

    }

}
