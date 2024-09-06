package com.pikachu.purple.infrastructure.persistence.mood.adaptor;

import com.pikachu.purple.application.mood.port.out.MoodRepository;
import com.pikachu.purple.domain.mood.Mood;
import com.pikachu.purple.infrastructure.persistence.mood.entity.MoodJpaEntity;
import com.pikachu.purple.infrastructure.persistence.mood.repository.MoodJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoodJpaAdaptor implements MoodRepository {

    private final MoodJpaRepository moodJpaRepository;

    @Override
    public List<Mood> findAll() {
        List<MoodJpaEntity> evaluationMoodJpaEntities = moodJpaRepository.findAll();

        return evaluationMoodJpaEntities.stream()
            .map(MoodJpaEntity::toDomain)
            .toList();
    }

}
