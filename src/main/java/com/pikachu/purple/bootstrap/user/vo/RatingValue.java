package com.pikachu.purple.bootstrap.user.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RatingValue {

    private final Long perfumeId;
    private final String perfumeName;
    private final Double score;

}
