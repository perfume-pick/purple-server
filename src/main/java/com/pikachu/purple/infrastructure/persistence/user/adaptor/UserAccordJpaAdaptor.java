package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import com.pikachu.purple.application.user.port.out.UserAccordRepository;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserAccordJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserAccordJpaAdaptor implements UserAccordRepository {

    private final UserAccordJpaRepository userAccordJpaRepository;

    @Override
    public void createAll(Long userId, List<UserAccord> userAccords) {
        List<UserAccordJpaEntity> userAccordJpaEntities = userAccords.stream()
            .map(userAccord -> userAccordJpaRepository.findByUserIdAndAccord(
                userId,
                userAccord.getAccord()
            ).map(existedEntity -> {
                existedEntity.addScore(userAccord.getScore());
                return existedEntity;
            }).orElseGet(() -> UserAccordJpaEntity.builder()
                    .userId(userId)
                    .accord(userAccord.getAccord())
                    .score(userAccord.getScore())
                    .build())
            ).toList();

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

    @Override
    public List<UserAccord> findAllByUserId(Long userId) {
        List<UserAccordJpaEntity> userAccordJpaEntities = userAccordJpaRepository.findAllByUserId(userId);

        return userAccordJpaEntities.stream()
            .map(UserAccordJpaEntity::toDomain)
            .toList();
    }

}
