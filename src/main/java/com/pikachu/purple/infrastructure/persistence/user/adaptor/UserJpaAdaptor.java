package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.NickNameAlreadyException;
import static com.pikachu.purple.bootstrap.common.exception.BusinessException.UserNotFoundException;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJpaAdaptor implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User validateNotExistedEmail(String email) {
        return userJpaRepository.findByEmail(email)
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
    public void validateNotExistedNickName(String nickName) {
        userJpaRepository.findByNickName(nickName)
            .ifPresent(userJpaEntity -> {
                throw NickNameAlreadyException;});
    }

}
