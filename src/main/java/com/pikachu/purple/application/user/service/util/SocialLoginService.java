package com.pikachu.purple.application.user.service.util;

import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;

public interface SocialLoginService {

    Url createUri(SocialLoginProvider socialLoginProvider);

    SocialLoginToken getToken(
        SocialLoginProvider socialLoginProvider,
        String authorizationCode
    );


}
