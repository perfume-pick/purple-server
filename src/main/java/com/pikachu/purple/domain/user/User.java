package com.pikachu.purple.domain.user;

import com.pikachu.purple.domain.review.Review;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private Long id;
    private String nickname;
    private String email;
    private String imageUrl;
    private SocialLoginProvider socialLoginProvider;

    @Setter
    private List<UserAccord> accords;
    @Setter
    private List<Review> reviews;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User(Long id, String nickname, String email, String imageUrl,
        SocialLoginProvider socialLoginProvider) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
        this.socialLoginProvider = socialLoginProvider;
    }

}
