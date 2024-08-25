package com.pikachu.purple.application.perfume.util;

import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class RecommendNotesProvider {

    private static final Map<Integer, Double> weightMap = Map.of(
        5, 1.0,
        4, 0.8,
        3, 0.0,
        2, -0.7,
        1, -0.9
    );

    private static final Map<String, Double> noteScoreMap = new HashMap<>();

    public List<Note> getTopThreeNotes(
        List<Rating> ratings,
        List<PerfumeNote> perfumeNotes
    ) {
        noteScoreMap.clear();

        for (Rating rating : ratings) {
            Long perfumeId = rating.getPerfumeId();
            int score = rating.getScore();
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
        double weight = weightMap.get(score);
        return score * weight;
    }

}
