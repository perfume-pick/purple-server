package com.pikachu.purple.infrastructure.persistence.user.adaptor;

import com.pikachu.purple.application.useraccrod.port.out.UserAccordRepository;
import com.pikachu.purple.domain.user.UserAccord;
import com.pikachu.purple.infrastructure.persistence.user.entity.UserAccordJpaEntity;
import com.pikachu.purple.infrastructure.persistence.user.repository.UserAccordJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccordJpaAdaptor implements UserAccordRepository {

    private final UserAccordJpaRepository userAccordJpaRepository;

    @Override
    public void createAll(List<UserAccord> userAccords) {

    }

    @Override
    public List<UserAccord> findAllByUserId(Long userId) {
        List<UserAccordJpaEntity> userAccordJpaEntities = userAccordJpaRepository.findAllByUserId(
            userId);

        return userAccordJpaEntities.stream()
            .map(UserAccordJpaEntity::toDomain)
            .toList();
    }
}
