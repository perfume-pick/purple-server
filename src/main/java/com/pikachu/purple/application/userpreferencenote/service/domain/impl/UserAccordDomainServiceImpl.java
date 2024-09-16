package com.pikachu.purple.application.userpreferencenote.service.domain.impl;

import com.pikachu.purple.application.userpreferencenote.port.out.UserAccordRepository;
import com.pikachu.purple.application.userpreferencenote.service.domain.UserAccordDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.perfume.Note;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccordDomainServiceImpl implements UserAccordDomainService {

    private final UserAccordRepository userAccordRepository;

    @Override
    public void save(
        Long userId,
        List<Note> perfumeNotes
    ) {
        List<Long> userPreferenceNoteIds = IntStream.range(0, perfumeNotes.size())
            .mapToObj(i -> IdGenerator.generate())
            .toList();

        List<UserPreferenceNote> userPreferenceNotes = IntStream.range(0, userPreferenceNoteIds.size())
            .mapToObj(i -> UserPreferenceNote.builder()
                .userPreferenceNoteId(userPreferenceNoteIds.get(i))
                .userId(userId)
                .noteName(perfumeNotes.get(i).getNoteName())
                .build())
            .toList();

        userAccordRepository.save(userPreferenceNotes);
    }

    @Override
    public List<UserPreferenceNote> findAllByUserId(Long userId) {
        return userAccordRepository.findAllByUserId(userId);
    }
}
