package com.pikachu.purple.application.user.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.user.port.in.UserUpdateProfileUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateProfileApplicationService implements UserUpdateProfileUseCase {

    private final UserDomainService userDomainService;

    @Transactional
    @Override
    public void invoke(Command command) {
        Long userId = getCurrentUserAuthentication().userId();

        userDomainService.updateProfile(
            userId,
            command.nickname(),
            command.picture()
        );
    }

}
