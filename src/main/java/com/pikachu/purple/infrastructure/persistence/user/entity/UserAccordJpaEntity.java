package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.accord.enums.Accord;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.user.entity.id.UserAccordId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(UserAccordId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccordJpaEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

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
