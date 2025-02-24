package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.application.util.IdUtil;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public void create(
        String email,
        String nickname,
        SocialLoginProvider socialLoginProvider
    ) {
        User user = User.builder()
            .id(IdUtil.generateId())
            .email(email)
            .nickname(nickname)
            .socialLoginProvider(socialLoginProvider)
            .build();

        userRepository.create(user);
    }

    @Override
    public User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        return userRepository.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
        );
    }

}
