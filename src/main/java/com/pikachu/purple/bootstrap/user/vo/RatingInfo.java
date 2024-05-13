package com.pikachu.purple.bootstrap.user.vo;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class RatingInfo {

    private final Long userId;
    private final List<RatingValue> ratingValueList;

}
