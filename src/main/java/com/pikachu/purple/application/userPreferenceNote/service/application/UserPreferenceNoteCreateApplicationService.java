package com.pikachu.purple.application.userPreferenceNote.service.application;

import static com.pikachu.purple.support.security.SecurityProvider.getCurrentUserAuthentication;

import com.pikachu.purple.application.review.port.in.ReviewGetUseCase;
import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteCreateUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetUseCase;
import com.pikachu.purple.application.perfume.util.RecommendNotesProvider;
import com.pikachu.purple.application.rating.port.in.RatingGetUseCase;
import com.pikachu.purple.application.userPreferenceNote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import com.pikachu.purple.domain.review.Review;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteCreateApplicationService implements
    UserPreferenceNoteCreateUseCase {

    private final RatingGetUseCase ratingGetUseCase;
    private final ReviewGetUseCase reviewGetUseCase;
    private final PerfumeNoteGetUseCase perfumeNoteGetByRatingsUseCase;
    private final RecommendNotesProvider recommendNotesProvider;
    private final UserPreferenceNoteDomainService userPreferenceNoteDomainService;

    @Override
    public void invoke() {
        Long userId = getCurrentUserAuthentication().userId();

        List<Review> reviews = reviewGetUseCase.findAllByUserId(userId);
        List<Rating> ratings = ratingGetUseCase.getAllByUserId(userId);

        List<Long> perfumeIds = reviews.stream()
            .map(Review::getPerfumeId)
            .toList();

        List<PerfumeNote> perfumeNotes = perfumeNoteGetByRatingsUseCase.getAllByPerfumeIds(perfumeIds);

        List<Note> topThreeNotes = recommendNotesProvider.getTopThreeNotes(
            reviews,
            ratings,
            perfumeNotes
        );

        List<Long> userPreferenceNoteIds = IntStream.range(0, topThreeNotes.size())
                .mapToObj(i -> IdGenerator.generate())
                .toList();

        userPreferenceNoteDomainService.save(
            userPreferenceNoteIds,
            userId,
            topThreeNotes
        );
    }

}
