package com.pikachu.purple.application.user.port.in;

import com.pikachu.purple.common.vo.Url;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URI;

public interface SocialLoginTryUseCase {

    Result invoke(SocialLoginTryUseCase.Command command);

    record Command(
        SocialLoginProvider socialLoginProvider
    ) {
    }

    record Result(
        Url socialLoginUrl
    ) {
    }

}
