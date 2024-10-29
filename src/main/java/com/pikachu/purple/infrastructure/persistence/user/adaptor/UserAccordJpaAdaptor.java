package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.AccordNotFountException;

import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.bootstrap.common.exception.BusinessException;
import com.pikachu.purple.bootstrap.common.exception.ErrorCode;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.accord.entity.AccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.accord.repository.AccordJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserAccordJpaRepository;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccordJpaAdaptor implements UserAccordRepository {

    private final UserAccordJpaRepository userAccordJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final AccordJpaRepository accordJpaRepository;

    @Override
    public void createAll(Long userId, List<UserAccord> userAccords) {
        UserJpaEntity userJpaEntity = userJpaRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        List<UserAccordJpaEntity> userAccordJpaEntities = userAccords.stream()
            .map(userAccord -> {
                AccordJpaEntity accordJpaEntity = accordJpaRepository.findByName(userAccord.getName())
                    .orElseThrow(() -> AccordNotFountException);

                return UserAccordJpaEntity.builder()
                    .userJpaEntity(userJpaEntity)
                    .accordJpaEntity(accordJpaEntity)
                    .score(userAccord.getScore())
                    .build();
            }).toList();

        userAccordJpaRepository.saveAll(userAccordJpaEntities);
    }

    @Override
    public List<UserAccord> findAllOrderByScoreDesc(
        Long userId,
        int maxSize
    ) {
        List<UserAccordJpaEntity> userAccordJpaEntities = userAccordJpaRepository.findAllByUserIdOrderByScoreDesc(
            userId,
            Limit.of(maxSize)
        );

        return userAccordJpaEntities.stream()
            .map(UserAccordJpaEntity::toDomain)
            .toList();
    }

    @Override
    public List<UserAccord> findAllOrderByScoreAsc(Long userId, int maxSize) {
        List<UserAccordJpaEntity> userAccordJpaEntities = userAccordJpaRepository.findAllByUserIdOrderByScoreAsc(
            userId,
            Limit.of(maxSize)
        );

        return userAccordJpaEntities.stream()
            .map(UserAccordJpaEntity::toDomain)
            .toList();
    }
}
