package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;

import com.pikachu.purple.application.user.service.util.SocialLoginUriService;
import java.net.URI;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginTryApplicationService implements SocialLoginTryUseCase {

    private final SocialLoginUriService socialLoginUriService;

    @Override
    public SocialLoginTryUseCase.Result invoke(SocialLoginTryUseCase.Command command) {
        URI socialLoginUri = socialLoginUriService.createUri(command.socialLoginProvider());

        return new SocialLoginTryUseCase.Result(socialLoginUri);
    }

}
