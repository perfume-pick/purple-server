package com.pikachu.purple.application.user.port.out;

import com.pikachu.purple.application.user.vo.SocialLoginTokenRequest;
import com.pikachu.purple.domain.user.vo.SocialLoginToken;

public interface SocialLoginPort {

    SocialLoginToken getToken(SocialLoginTokenRequest socialLoginTokenRequest);

}
