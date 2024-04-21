package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.ConfirmEmailVerificationCodeUseCase;
import com.pikachu.purple.application.user.port.out.UserEmailVerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmEmailVerificationCodeService implements ConfirmEmailVerificationCodeUseCase {

    private final UserEmailVerificationRepository userEmailVerificationRepository;

    @Override
    public void invoke(
        String email,
        String verifyCode
    ) {
        userEmailVerificationRepository.confirm(
            email,
            verifyCode
        );
    }

}
