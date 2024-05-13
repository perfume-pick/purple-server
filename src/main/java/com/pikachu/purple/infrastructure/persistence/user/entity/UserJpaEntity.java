package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registered_at", nullable = false)
    private LocalDateTime registeredAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_login_provider", nullable = false)
    private SocialLoginProvider socialLoginProvider;

    @Builder
    public UserJpaEntity(
        Long id,
        String email,
        String nickname,
        LocalDateTime registeredAt,
        SocialLoginProvider socialLoginProvider
    ){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.registeredAt = registeredAt;
        this.socialLoginProvider = socialLoginProvider;
    }

    public static UserJpaEntity toJpaEntity(User user){
        return UserJpaEntity.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .registeredAt(user.getRegisteredAt())
            .socialLoginProvider(user.getSocialLoginProvider())
            .build();
    }

    public static User toDomain(UserJpaEntity entity){
        return User.builder()
            .id(entity.getId())
            .email(entity.getEmail())
            .nickname(entity.getNickname())
            .registeredAt(entity.getRegisteredAt())
            .socialLoginProvider(entity.getSocialLoginProvider())
            .build();
    }

}
