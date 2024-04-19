package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
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
                throw BusinessException.EmailExistedException;});
    }

}
