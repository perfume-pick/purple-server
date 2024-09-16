package com.pikachu.purple.application.userpreferencenote.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.userpreferencenote.port.in.UserPreferenceNoteCreateUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetUseCase;
import com.pikachu.purple.application.perfume.util.RecommendNotesProvider;
import com.pikachu.purple.application.rating.port.in.RatingGetUseCase;
import com.pikachu.purple.application.userpreferencenote.service.domain.UserAccordDomainService;
import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.review.StarRating;
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
    private final UserAccordDomainService userAccordDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<StarRating> starRatings = ratingGetUseCase.getAllByUserId(userId);

        List<Long> perfumeIds = starRatings.stream()
            .map(StarRating::getPerfumeId)
            .toList();

        List<PerfumeNote> perfumeNotes = perfumeNoteGetByRatingsUseCase.getAllByPerfumeIds(perfumeIds);

        List<Note> topThreeNotes = recommendNotesProvider.getTopThreeNotes(
            starRatings,
            perfumeNotes
        );

        userAccordDomainService.save(
            userId,
            topThreeNotes
        );
    }

}
