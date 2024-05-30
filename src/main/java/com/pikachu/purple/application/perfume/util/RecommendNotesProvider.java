package com.pikachu.purple.application.perfume.util;

import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RecommendNotesProvider {

    private static final Map<Double, Double> weightMap = new HashMap<>();

    static{
        weightMap.put(5.0, 1.0);
        weightMap.put(4.5, 0.9);
        weightMap.put(4.0, 0.8);
        weightMap.put(3.5, 0.7);
        weightMap.put(3.0, 0.0);
        weightMap.put(2.5, 0.0);
        weightMap.put(2.0, -0.7);
        weightMap.put(1.5, -0.8);
        weightMap.put(1.0, -0.9);
        weightMap.put(0.5, -1.0);
    }

    public List<Note> getTopThreeNotes(
        List<Rating> ratingList,
        List<PerfumeNote> perfumeNoteList
    ) {
        Map<String, Double> noteScoreMap = new HashMap<>();

        for (Rating rating : ratingList) {
            Long perfumeId = rating.getPerfumeId();
            Double score = rating.getScore();
            Double weightedScore = convert(score);

            for (PerfumeNote note : perfumeNoteList) {
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
            .collect(Collectors.toList());
    }

    private double convert(double score){
        double weight = weightMap.get(score);
        return score * weight;
    }

}
