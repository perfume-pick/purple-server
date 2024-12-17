package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.accord.entity.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.id.UserAccordId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accord_name")
    private AccordJpaEntity accordJpaEntity;

    private double score;

    public static UserAccord toDomain(UserAccordJpaEntity jpaEntity) {
        UserAccord domain = new UserAccord(
            jpaEntity.getAccordJpaEntity().getName(),
            jpaEntity.getAccordJpaEntity().getKoreanName(),
            jpaEntity.getScore()
        );
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserJpaEntity()));

        return domain;
    }

    public void addScore(double newScore) {
        this.score = this.score + newScore;
    }

}
