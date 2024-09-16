package com.pikachu.purple.application.perfume.util;

import com.pikachu.purple.domain.perfume.Note;
import com.pikachu.purple.domain.review.StarRating;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class RecommendNotesProvider {

    private static final double[] weights = {-0.9, -0.7, 0.0, 0.8, 1.0};
    private static final Map<String, Double> noteScoreMap = new HashMap<>();

    public List<Note> getTopThreeNotes(
        List<StarRating> starRatings,
        List<PerfumeNote> perfumeNotes
    ) {
        noteScoreMap.clear();

        for (StarRating starRating : starRatings) {
            Long perfumeId = starRating.getPerfumeId();
            int score = starRating.getScore();
            Double weightedScore = convert(score);

            for (PerfumeNote note : perfumeNotes) {
                if (note.getPerfumeId().equals(perfumeId)) {
                    noteScoreMap.merge(
                        note.getNoteName(),
                        weightedScore,
                        Double::sum
                    );
                }
            }
        }

        return noteScoreMap.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(3)
            .map(entry -> new Note(entry.getKey()))
            .toList();
    }

    private double convert(int score){
        return score * weights[score-1];
    }

}
