package com.pikachu.purple.application.user.service.application;

import com.pikachu.purple.application.user.port.in.SignUpUseCase;
import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserDomainService userDomainService;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void invoke(String email) {
        User user = userRepository.validateNotExistedEmail(email);

        if(user == null) {
            User createUser = User.create(email);
            userDomainService.create(createUser);
        }
    }
}
