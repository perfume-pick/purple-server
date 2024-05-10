package com.pikachu.purple.infrastructure.persistence.user.entity;

import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "Social_login_provider", nullable = false)
    private SocialLoginProvider socialLoginProvider;

    @Builder
    public UserJpaEntity(Long id, String nickname, String email,
        SocialLoginProvider socialLoginProvider) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.socialLoginProvider = socialLoginProvider;
    }

    public static UserJpaEntity toJpaEntity(User user){
        return UserJpaEntity.builder()
            .id(user.getId())
            .nickname(user.getNickname())
            .email(user.getEmail())
            .socialLoginProvider(user.getSocialLoginProvider())
            .build();
    }

    public User toDomain(){
        return User.builder()
            .id(this.id)
            .nickname(this.nickname)
            .email(this.email)
            .socialLoginProvider(this.socialLoginProvider)
            .build();
    }

}
