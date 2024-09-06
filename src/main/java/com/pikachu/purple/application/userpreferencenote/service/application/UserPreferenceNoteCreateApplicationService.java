package com.pikachu.purple.application.userpreferencenote.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userpreferencenote.port.in.UserPreferenceNoteCreateUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetUseCase;
import com.pikachu.purple.application.perfume.util.RecommendNotesProvider;
import com.pikachu.purple.application.rating.port.in.RatingGetUseCase;
import com.pikachu.purple.application.userpreferencenote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteCreateApplicationService implements
    UserPreferenceNoteCreateUseCase {

    private final RatingGetUseCase ratingGetUseCase;
    private final PerfumeNoteGetUseCase perfumeNoteGetByRatingsUseCase;
    private final RecommendNotesProvider recommendNotesProvider;
    private final UserPreferenceNoteDomainService userPreferenceNoteDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<Rating> ratings = ratingGetUseCase.getAllByUserId(userId);

        List<Long> perfumeIds = ratings.stream()
            .map(Rating::getPerfumeId)
            .toList();

        List<PerfumeNote> perfumeNotes = perfumeNoteGetByRatingsUseCase.getAllByPerfumeIds(perfumeIds);

        List<Note> topThreeNotes = recommendNotesProvider.getTopThreeNotes(
            ratings,
            perfumeNotes
        );

        userPreferenceNoteDomainService.save(
            userId,
            topThreeNotes
        );
    }

}
