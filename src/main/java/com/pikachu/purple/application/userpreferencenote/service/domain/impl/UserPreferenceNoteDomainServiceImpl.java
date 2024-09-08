package com.pikachu.purple.application.userpreferencenote.service.domain.impl;

import com.pikachu.purple.application.userpreferencenote.port.out.UserPreferenceNoteRepository;
import com.pikachu.purple.application.userpreferencenote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.user.UserPreferenceNote;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteDomainServiceImpl implements UserPreferenceNoteDomainService {

    private final UserPreferenceNoteRepository userPreferenceNoteRepository;

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

        userPreferenceNoteRepository.save(userPreferenceNotes);
    }

    @Override
    public List<UserPreferenceNote> getAllByUserId(Long userId) {
        return userPreferenceNoteRepository.getAllByUserId(userId);
    }
}
