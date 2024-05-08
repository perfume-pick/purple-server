package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SocialLoginTryUseCase;

import com.pikachu.purple.application.user.service.util.SocialLoginService;
import com.pikachu.purple.common.vo.Url;
import java.net.URI;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialLoginTryApplicationService implements SocialLoginTryUseCase {

    private final SocialLoginService socialLoginService;

    @Override
    public SocialLoginTryUseCase.Result invoke(SocialLoginTryUseCase.Command command) {
        Url socialLoginUri = socialLoginService.createUri(command.socialLoginProvider());

        return new SocialLoginTryUseCase.Result(socialLoginUri);
    }

}
