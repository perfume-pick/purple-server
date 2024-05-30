package com.pikachu.purple.application.userPreferenceNote.service.domain.impl;

import com.pikachu.purple.application.userPreferenceNote.port.out.UserPreferenceNoteRepository;
import com.pikachu.purple.application.userPreferenceNote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
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
        List<Long> userPreferenceNoteIdList,
        Long userId,
        List<Note> noteList
    ) {
       List<UserPreferenceNote> userPreferenceNoteList = IntStream.range(0, userPreferenceNoteIdList.size())
               .mapToObj(i -> UserPreferenceNote.builder()
                   .id(userPreferenceNoteIdList.get(i))
                   .userId(userId)
                   .noteName(noteList.get(i).getNoteName())
                   .build())
           .collect(Collectors.toList());

        userPreferenceNoteRepository.save(userPreferenceNoteList);
    }

    @Override
    public List<UserPreferenceNote> getByUserId(Long userId) {
        return userPreferenceNoteRepository.getByUserId(userId);
    }
}
