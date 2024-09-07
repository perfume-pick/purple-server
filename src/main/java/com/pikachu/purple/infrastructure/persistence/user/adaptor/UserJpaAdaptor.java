package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.NicknameAlreadyExistedException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaAdaptor implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        return userJpaRepository.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
            )
            .map(UserJpaEntity::toDomain)
            .orElse(null);
    }

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.toJpaEntity(user);
        UserJpaEntity savedUserJpaEntity = userJpaRepository.save(userJpaEntity);

        return UserJpaEntity.toDomain(savedUserJpaEntity);
    }

    @Override
    public User getById(Long userId) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);

        return UserJpaEntity.toDomain(userJpaEntity);
    }

    @Override
    public void validateNotExistedNickname(String nickname) {
        userJpaRepository.findByNickname(nickname)
            .ifPresent(userJpaEntity -> {throw NicknameAlreadyExistedException;});
    }

    @Override
    public int countAll() {
        return userJpaRepository.countAll();
    }

}
