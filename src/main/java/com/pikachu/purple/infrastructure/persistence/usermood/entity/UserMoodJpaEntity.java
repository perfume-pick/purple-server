package com.pikachu.purple.infrastructure.persistence.usermood.entity;

import com.pikachu.purple.domain.user.entity.UserMood;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_mood")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMoodJpaEntity extends BaseEntity {

    @Id
    @Column(name = "user_mood_id")
    private Long userMoodId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "perfume_id", nullable = false)
    private Long perfumeId;

    @Column(name = "mood_name", nullable = false)
    private String moodName;

    @Builder
    public UserMoodJpaEntity(
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

    public static UserMoodJpaEntity toJpaEntity(UserMood userMood) {
        return UserMoodJpaEntity.builder()
            .userMoodId(userMood.getUserMoodId())
            .userId(userMood.getUserId())
            .perfumeId(userMood.getPerfumeId())
            .moodName(userMood.getMoodName())
            .build();
    }

}
