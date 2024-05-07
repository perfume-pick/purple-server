package com.pikachu.purple.domain.user;

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
    private LocalDateTime registerAt;
    private SocialLoginProvider socialLoginProvider;

    @Builder
    public User(
        Long id,
        String email,
        String nickname,
        LocalDateTime registerAt,
        SocialLoginProvider socialLoginProvider
    ){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.registerAt = registerAt;
        this.socialLoginProvider = socialLoginProvider;
    }

    public static User create(String email, SocialLoginProvider socialLoginProvider) {
        return new User(
            null,
            email,
            "",
            LocalDateTime.now(),
            socialLoginProvider
        );
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
