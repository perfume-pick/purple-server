package com.pikachu.purple.infrastructure.persistence.review.entity;

import com.pikachu.purple.domain.review.Complaint;
import com.pikachu.purple.infrastructure.persistence.common.BaseEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
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
@Table(name = "complaint")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ComplaintJpaEntity extends BaseEntity {

    @Id
    @Column(name = "complaint_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private ReviewJpaEntity reviewJpaEntity;

    @Column(name = "token", nullable = false)
    private String token;

    public static Complaint toDomain(ComplaintJpaEntity jpaEntity) {
        Complaint domain = new Complaint(
            jpaEntity.getId(),
            jpaEntity.getToken(),
            jpaEntity.getCreatedAt()
        );

        domain.setReview(ReviewJpaEntity.toDummy(jpaEntity.getReviewJpaEntity()));
        domain.setUser(UserJpaEntity.toDummy(jpaEntity.getUserJpaEntity()));

        return domain;
    }

}
