package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.user.UserAccord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "user_accord")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccordJpaEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "accord_name",
        columnDefinition = "varchar(255)",
        nullable = false
    )
    private Accord accord;

    private double score;

    public static UserAccord toDomain(UserAccordJpaEntity jpaEntity) {
        UserAccord domain = new UserAccord(
            jpaEntity.getAccord(),
            jpaEntity.getScore()
        );
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserId()));

        return domain;
    }

    public void addScore(double newScore) {
        this.score = this.score + newScore;
    }

}
