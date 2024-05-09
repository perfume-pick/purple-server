package com.pikachu.purple.domain.user.entity;

import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String nickname;
    private String email;
    private SocialLoginProvider socialLoginProvider;

    @Builder
    public User(
        Long id,
        String nickname,
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.socialLoginProvider = socialLoginProvider;
    }

}
