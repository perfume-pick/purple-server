package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.NicknameAlreadyExistsException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserJpaAdaptor implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    private UserJpaEntity findEntityById(Long userId) {
        return userJpaRepository.findById(userId)
            .orElseThrow(() -> UserNotFoundException);
    }

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
    public void create(User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.toJpaEntity(user);
        userJpaRepository.save(userJpaEntity);
    }

    @Override
    public User update(User user) {
        UserJpaEntity userJpaEntity = findEntityById(user.getId());
        userJpaEntity.update(user);
        UserJpaEntity savedUserJpaEntity = userJpaRepository.save(userJpaEntity);

        return UserJpaEntity.toDomain(savedUserJpaEntity);
    }

    @Override
    public void validateNotExistedNickname(String nickname) {
        userJpaRepository.findByNickname(nickname)
            .ifPresent(userJpaEntity -> {throw NicknameAlreadyExistsException;});
    }

    @Override
    public int count() {
        return userJpaRepository.countAll();
    }

    /*
    User 반환
     */
    @Override
    public User findById(Long userId) {
        UserJpaEntity userJpaEntity = findEntityById(userId);

        return UserJpaEntity.toDomain(userJpaEntity);
    }

}
