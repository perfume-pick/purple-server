package com.pikachu.purple.application.perfume.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.perfume.port.in.PerfumeGetByUserPreferenceNoteUseCase;
import com.pikachu.purple.application.perfume.service.domain.PerfumeDomainService;
import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteGetUseCase;
import com.pikachu.purple.domain.perfume.Perfume;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfumeGetByUserPreferenceNoteApplicationService implements PerfumeGetByUserPreferenceNoteUseCase {

    private final PerfumeDomainService perfumeDomainService;
    private final UserPreferenceNoteGetUseCase userPreferenceNoteGetUseCase;

    @Override
    public Result invoke() {
        Long userId = getCurrentUserAuthentication().userId();
        List<Perfume> perfumeList = perfumeDomainService.findByUserPreferenceNotes(userId);
        UserPreferenceNoteGetUseCase.Result userPreferenceNoteList = userPreferenceNoteGetUseCase.invoke();

        return new Result(
            userPreferenceNoteList.userPreferenceNoteList(),
            perfumeList
        );
    }

}
