package com.pikachu.purple.application.user.service.domain.impl;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.application.user.service.domain.UserDomainService;
import com.pikachu.purple.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public void create(User createUser) {
        User user = User.builder()
            .id(createUser.getId())
            .email(createUser.getEmail())
            .nickName(createUser.getNickName())
            .registerDate(createUser.getRegisterDate())
            .socialLoginProvider(createUser.getSocialLoginProvider())
            .build();

        userRepository.save(user);
    }

    @Override
    public void updateNickNameById(Long userId, String nickName) {
        User user = userRepository.getById(userId);

        userRepository.validateNotExistedNickName(nickName);
        user.updateNickName(nickName);

        userRepository.save(user);
    }
}
