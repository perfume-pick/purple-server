package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;
import com.pikachu.purple.application.user.service.util.SocialLoginService;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginTryApplicationService implements SocialLoginTryUseCase {

    private final SocialLoginService socialLoginService;

    @Override
    public SocialLoginTryUseCase.Result invoke(Command command)
        throws URISyntaxException {
        URI socialLoginUri = socialLoginService.createUri(
            command.socialLoginProvider(),
            command.frontUrl()
        );

        return new SocialLoginTryUseCase.Result(socialLoginUri.toString());
    }

}
