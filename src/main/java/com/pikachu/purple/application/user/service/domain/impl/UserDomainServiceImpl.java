package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public void create(User createdUser) {
        User user = User.builder()
            .id(createdUser.getId())
            .email(createdUser.getEmail())
            .nickname(createdUser.getNickname())
            .registeredAt(createdUser.getRegisteredAt())
            .socialLoginProvider(createdUser.getSocialLoginProvider())
            .build();

        userRepository.save(user);
    }

    @Override
    public void updateNicknameById(
        Long userId,
        String nickname
    ) {
        User user = userRepository.getById(userId);

        userRepository.validateNotExistedNickname(nickname);
        user.updateNickname(nickname);

        userRepository.save(user);
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

    @Override
    public int countAll() {
        return userRepository.countAll();
    }

}
