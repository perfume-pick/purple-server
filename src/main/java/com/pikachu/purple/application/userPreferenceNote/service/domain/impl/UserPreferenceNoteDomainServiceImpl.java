package com.pikachu.purple.application.userPreferenceNote.service.domain.impl;

import com.pikachu.purple.application.userPreferenceNote.port.out.UserPreferenceNoteRepository;
import com.pikachu.purple.application.userPreferenceNote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteDomainServiceImpl implements UserPreferenceNoteDomainService {

    private final UserPreferenceNoteRepository userPreferenceNoteRepository;

    @Override
    public void save(
        List<Long> userPreferenceNoteIds,
        Long userId,
        List<Note> perfumeNotes
    ) {
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
