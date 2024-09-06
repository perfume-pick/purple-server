package com.pikachu.purple.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMood {

    private Long userMoodId;
    private Long userId;
    private Long perfumeId;
    private String moodName;

    @Builder
    public UserMood(
        Long userMoodId,
        Long userId,
        Long perfumeId,
        String moodName
    ) {
        this.userMoodId = userMoodId;
        this.userId = userId;
        this.perfumeId = perfumeId;
        this.moodName = moodName;
    }

}
