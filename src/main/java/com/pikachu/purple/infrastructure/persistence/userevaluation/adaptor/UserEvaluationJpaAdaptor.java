package com.pikachu.purple.infrastructure.persistence.userevaluation.adaptor;

import com.pikachu.purple.application.userevaluation.port.out.UserEvaluationRepository;
import com.pikachu.purple.domain.user.UserEvaluation;
import com.pikachu.purple.infrastructure.persistence.userevaluation.entity.UserEvaluationJpaEntity;
import com.pikachu.purple.infrastructure.persistence.userevaluation.repository.UserEvaluationJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEvaluationJpaAdaptor implements UserEvaluationRepository {

    private final UserEvaluationJpaRepository userEvaluationJpaRepository;

    @Override
    public void create(List<UserEvaluation> userEvaluations) {
        List<UserEvaluationJpaEntity> userEvaluationJpaEntities = userEvaluations.stream()
            .map(UserEvaluationJpaEntity::toJpaEntity)
            .toList();

        userEvaluationJpaRepository.saveAll(userEvaluationJpaEntities);
    }

}
