package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserSignUpUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.vo.Nickname;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class UserSignUpApplicationService implements UserSignUpUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void invoke(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        User user = userDomainService.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
        );

        if(user == null) {
            userDomainService.create(
                email,
                new Nickname(userDomainService.count()).getValue(),
                socialLoginProvider
            );
        }
    }

}
