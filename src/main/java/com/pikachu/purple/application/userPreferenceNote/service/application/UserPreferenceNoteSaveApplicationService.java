package com.pikachu.purple.application.userPreferenceNote.service.application;

import com.pikachu.purple.application.userPreferenceNote.port.in.UserPreferenceNoteSaveUseCase;
import com.pikachu.purple.application.perfume.port.in.PerfumeNoteGetByRatingsUseCase;
import com.pikachu.purple.application.perfume.util.RecommendNotesProvider;
import com.pikachu.purple.application.rating.port.in.RatingGetByUserIdUseCase;
import com.pikachu.purple.application.userPreferenceNote.service.domain.UserPreferenceNoteDomainService;
import com.pikachu.purple.application.util.IdGenerator;
import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.rating.Rating;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPreferenceNoteSaveApplicationService implements
    UserPreferenceNoteSaveUseCase {

    private final RatingGetByUserIdUseCase ratingGetByUserIdUseCase;
    private final PerfumeNoteGetByRatingsUseCase perfumeNoteGetByRatingsUseCase;
    private final RecommendNotesProvider recommendNotesProvider;
    private final UserPreferenceNoteDomainService userPreferenceNoteDomainService;

    @Override
    public void invoke() {
        RatingGetByUserIdUseCase.Result ratingResult = ratingGetByUserIdUseCase.invoke(1L);

        List<Long> perfumeIdList = ratingResult.ratingList().stream()
            .map(Rating::getPerfumeId)
            .toList();

        PerfumeNoteGetByRatingsUseCase.Result perfumeNoteResult = perfumeNoteGetByRatingsUseCase.invoke(perfumeIdList);

        List<Note> topThreeNoteList = recommendNotesProvider.getTopThreeNotes(
            ratingResult.ratingList(),
            perfumeNoteResult.perfumeNoteList()
        );

        List<Long> userPreferenceNoteIdList = IntStream.range(0, topThreeNoteList.size())
                .mapToObj(i -> IdGenerator.generate())
                .toList();

        userPreferenceNoteDomainService.save(
            userPreferenceNoteIdList,
            1L,
            topThreeNoteList
        );
    }

}
