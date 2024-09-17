package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
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
@Table(name = "user")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_login_provider", nullable = false)
    private SocialLoginProvider socialLoginProvider;

    public static UserJpaEntity toJpaEntity(User domain){
        return UserJpaEntity.builder()
            .id(domain.getId())
            .email(domain.getEmail())
            .nickname(domain.getNickname())
            .imageUrl(domain.getImageUrl())
            .socialLoginProvider(domain.getSocialLoginProvider())
            .build();
    }

    public static User toDomain(UserJpaEntity jpaEntity){
        return User.builder()
            .id(jpaEntity.getId())
            .email(jpaEntity.getEmail())
            .nickname(jpaEntity.getNickname())
            .imageUrl(jpaEntity.getImageUrl())
            .socialLoginProvider(jpaEntity.getSocialLoginProvider())
            .build();
    }

}
