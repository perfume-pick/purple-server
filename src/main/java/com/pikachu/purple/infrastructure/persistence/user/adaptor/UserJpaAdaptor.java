package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import com.pikachu.purple.application.user.port.out.UserRepository;
import com.pikachu.purple.domain.user.entity.User;
import com.pikachu.purple.domain.user.enums.SocialLoginProvider;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserJpaAdaptor implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void validateNotExistedEmail(String email) {
//        userJpaRepository.findByEmail(email)
//            .ifPresent(userEntity -> {
//                throw BusinessException.EmailExistedException;
//            }
//        );
    }

    @Override
    public User findByEmailAndSocialLoginProvider(
        String email,
        SocialLoginProvider socialLoginProvider
    ) {
        Optional<UserJpaEntity> result = userJpaRepository.findByEmailAndSocialLoginProvider(
            email,
            socialLoginProvider
        );

        return result.map(UserJpaEntity::toDomain).orElse(null);
    }

}
