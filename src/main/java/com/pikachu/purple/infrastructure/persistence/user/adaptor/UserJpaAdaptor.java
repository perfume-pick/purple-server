package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.EmailExistedException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.NicknameAlreadyException;
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
public class UserJpaAdaptor implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void validateNotExistedEmail(String email) {
        userJpaRepository.findByEmail(email)
            .ifPresent(userEntity -> {
                throw EmailExistedException;});
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
    public void save(User user) {
        UserJpaEntity userJpaEntity = UserJpaEntity.toJpaEntity(user);
        userJpaRepository.save(userJpaEntity);
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
            .ifPresent(userJpaEntity -> {
                throw NicknameAlreadyException;});
    }

    @Override
    public int countAll() {
        return userJpaRepository.countAll();
    }

}
