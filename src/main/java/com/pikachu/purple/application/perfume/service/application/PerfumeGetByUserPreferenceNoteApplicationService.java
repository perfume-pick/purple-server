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
        List<Perfume> perfumeList = perfumeDomainService.findByUserPreferenceNotes(
            getCurrentUserAuthentication().userId());
        UserPreferenceNoteGetUseCase.Result result = userPreferenceNoteGetUseCase.invoke();

        return new Result(
            result.userPreferenceNoteList(),
            perfumeList
        );
    }

}
