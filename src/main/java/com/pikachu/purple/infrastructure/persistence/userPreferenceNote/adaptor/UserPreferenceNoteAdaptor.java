package com.pikachu.purple.infrastructure.persistence.userPreferenceNote.adaptor;

import com.pikachu.purple.application.userPreferenceNote.port.out.UserPreferenceNoteRepository;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import com.pikachu.purple.infrastructure.persistence.userPreferenceNote.entity.UserPreferenceNoteJpaEntity;
import com.pikachu.purple.infrastructure.persistence.userPreferenceNote.repository.UserPreferenceNoteJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPreferenceNoteAdaptor implements UserPreferenceNoteRepository {

    private final UserPreferenceNoteJpaRepository userPreferenceNoteJpaRepository;

    @Override
    public void save(List<UserPreferenceNote> userPreferenceNoteList) {
        List<UserPreferenceNoteJpaEntity> userPreferenceNoteJpaEntityList = userPreferenceNoteList.stream()
            .map(UserPreferenceNoteJpaEntity::toJpaEntity)
            .toList();

        userPreferenceNoteJpaRepository.saveAll(userPreferenceNoteJpaEntityList);
    }

    @Override
    public List<UserPreferenceNote> getByUserId(Long userId) {
        List<UserPreferenceNoteJpaEntity> userPreferenceNoteJpaEntityList = userPreferenceNoteJpaRepository.findByUserId(userId);
        return userPreferenceNoteJpaEntityList.stream()
            .map(UserPreferenceNoteJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

}
