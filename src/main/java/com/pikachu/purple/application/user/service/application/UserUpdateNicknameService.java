package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.UserUpdateNicknameUseCase;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateNicknameService implements UserUpdateNicknameUseCase {

    private final UserDomainService userDomainService;

    @Override
    public void invoke(Command command) {
        userDomainService.updateNicknameById(
            command.userId(),
            command.nickname()
        );
    }

}
