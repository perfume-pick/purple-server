package com.pikachu.purple.application.perfume.util;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.ReviewNotFoundException;

import com.pikachu.purple.domain.note.Note;
import com.pikachu.purple.domain.perfume.PerfumeNote;
import com.pikachu.purple.domain.rating.Rating;
import com.pikachu.purple.domain.review.Review;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class RecommendNotesProvider {

    private static final Map<Double, Double> weightMap = Map.of(
        5.0, 1.0,
        4.5, 0.9,
        4.0, 0.8,
        3.5, 0.7,
        3.0, 0.0,
        2.5, 0.0,
        2.0, -0.7,
        1.5, -0.8,
        1.0, -0.9,
        0.5, -1.0
    );

    private static final Map<String, Double> noteScoreMap = new HashMap<>();

    public List<Note> getTopThreeNotes(
        List<Review> reviews,
        List<Rating> ratings,
        List<PerfumeNote> perfumeNotes
    ) {
        noteScoreMap.clear();

        for (Rating rating : ratings) {
            Long perfumeId = findPerfumeId(reviews, rating);
            Double score = rating.getScore();
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

    private Long findPerfumeId(List<Review> reviews, Rating rating) {
        return reviews.stream()
            .filter(review -> review.getReviewId().equals(rating.getReviewId()))
            .map(Review::getPerfumeId)
            .findFirst()
            .orElseThrow(() -> ReviewNotFoundException);
    }

    private double convert(double score){
        double weight = weightMap.get(score);
        return score * weight;
    }

}
