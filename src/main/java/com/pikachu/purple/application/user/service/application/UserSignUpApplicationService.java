package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserSignUpUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.user.vo.Nickname;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignUpApplicationService implements UserSignUpUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        User user = userDomainService.findByEmailAndSocialLoginProvider(
            command.email(),
            command.socialLoginProvider()
        );

        if(user == null) {
            userDomainService.create(
                command.email(),
                new Nickname(userDomainService.count()).getValue(),
                command.socialLoginProvider()
            );
        }
    }
}
