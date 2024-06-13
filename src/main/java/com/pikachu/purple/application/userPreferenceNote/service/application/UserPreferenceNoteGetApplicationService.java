package com.pikachu.purple.application.userPreferenceNote.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteGetUseCase;
import com.pikachu.purple.application.userPreferenceNote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.domain.user.entity.UserPreferenceNote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteGetApplicationService implements UserPreferenceNoteGetUseCase {

    private final UserPreferenceNoteDomainService userPreferenceNoteDomainService;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        List<UserPreferenceNote> userPreferenceNoteList = userPreferenceNoteDomainService.getByUserId(userId);

        return new Result(userPreferenceNoteList);
    }

}
