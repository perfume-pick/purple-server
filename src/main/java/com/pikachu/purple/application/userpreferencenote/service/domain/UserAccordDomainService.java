package com.pikachu.purple.application.userpreferencenote.service.domain;

import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;

public interface UserAccordDomainService {

    void save(
        Long userId,
        List<Note> perfumeNotes
    );

    List<UserAccord> findAllByUserId(Long userId);

}
