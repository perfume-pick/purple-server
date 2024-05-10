package com.pikachu.purple.domain.user.entity;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String email;
    private String nickname;
    private LocalDateTime registeredAt;
    private SocialLoginProvider socialLoginProvider;

    @Builder
    public User(
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

    public static User create(Long id, String email, String nickname, SocialLoginProvider socialLoginProvider) {
        return new User(
            id,
            email,
            nickname,
            LocalDateTime.now(),
            socialLoginProvider
        );
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}
