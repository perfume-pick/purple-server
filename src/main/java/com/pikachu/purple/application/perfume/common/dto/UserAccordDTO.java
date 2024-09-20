package com.pikachu.purple.application.perfume.common.dto;

import com.pikachu.purple.domain.user.UserAccord;

public record UserAccordDTO(
    String accordName,
    int order
) {

    public static UserAccordDTO of(
        UserAccord userAccord,
        int order
    ){
        return new UserAccordDTO(
            userAccord.getName(),
            order
        );
    }

}
