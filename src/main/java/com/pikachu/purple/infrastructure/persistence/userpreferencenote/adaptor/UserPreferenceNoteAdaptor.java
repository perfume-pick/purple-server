package com.pikachu.purple.infrastructure.persistence.userpreferencenote.adaptor;

import com.pikachu.purple.application.userpreferencenote.port.out.UserPreferenceNoteRepository;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import com.pikachu.purple.infrastructure.persistence.userpreferencenote.entity.UserPreferenceNoteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.userpreferencenote.repository.UserPreferenceNoteJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPreferenceNoteAdaptor implements UserPreferenceNoteRepository {

    private final UserPreferenceNoteJpaRepository userPreferenceNoteJpaRepository;

    @Override
    public void save(List<UserPreferenceNote> userPreferenceNotes) {
        List<UserPreferenceNoteJpaEntity> userPreferenceNoteJpaEntities = userPreferenceNotes.stream()
            .map(UserPreferenceNoteJpaEntity::toJpaEntity)
            .toList();

        userPreferenceNoteJpaRepository.saveAll(userPreferenceNoteJpaEntities);
    }

    @Override
    public List<UserPreferenceNote> getAllByUserId(Long userId) {
        List<UserPreferenceNoteJpaEntity> userPreferenceNoteJpaEntities = userPreferenceNoteJpaRepository.findByUserId(userId);
        return userPreferenceNoteJpaEntities.stream()
            .map(UserPreferenceNoteJpaEntity::toDomain)
            .toList();
    }

}
