package com.pikachu.purple.infrastructure.persistence.usermood.adaptor;

import com.pikachu.purple.application.usermood.port.out.UserMoodRepository;
import com.pikachu.purple.domain.user.entity.UserMood;
import com.pikachu.purple.infrastructure.persistence.usermood.entity.UserMoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.usermood.repository.UserMoodJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMoodJpaAdaptor implements UserMoodRepository {

    private final UserMoodJpaRepository userMoodJpaRepository;

    @Override
    public void create(List<UserMood> userMoods) {
        List<UserMoodJpaEntity> userMoodJpaEntities = userMoods.stream()
            .map(UserMoodJpaEntity::toJpaEntity)
            .toList();

        userMoodJpaRepository.saveAll(userMoodJpaEntities);
    }

}
