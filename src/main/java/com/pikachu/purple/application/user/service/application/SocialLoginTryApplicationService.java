package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class SocialLoginTryApplicationService implements SocialLoginTryUseCase {

    private final SocialLoginService socialLoginService;

    @Override
    public Result invoke(
        SocialLoginProvider socialLoginProvider,
        String frontUrl
    ) throws URISyntaxException {
        URI socialLoginUri = socialLoginService.createUri(
            socialLoginProvider,
            frontUrl
        );

        return new Result(socialLoginUri.toString());
    }

}
