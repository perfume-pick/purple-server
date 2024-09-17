package com.pikachu.purple.application.perfume.util;

import com.pikachu.purple.domain.perfume.PerfumeAccord;
import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RecommendUserAccordsProvider {

    private static final double[] weights = {-0.9, -0.7, 0.0, 0.8, 1.0};
    private static final Map<String, Double> perfumeAccordScoreMap = new HashMap<>();

    public List<UserAccord> getTopThreeUserAccords(
        User user,
        List<StarRating> starRatings
    ) {
        perfumeAccordScoreMap.clear();

        for (StarRating starRating : starRatings) {
            int score = starRating.getScore();
            double weightedScore = convert(score);

            List<PerfumeAccord> perfumeAccords = starRating.getPerfume().getAccords();

            for (PerfumeAccord perfumeAccord : perfumeAccords) {
                perfumeAccordScoreMap.merge(
                    perfumeAccord.getName(),
                    weightedScore * perfumeAccord.getValue(),
                    Double::sum
                );
            }
        }

        return perfumeAccordScoreMap.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(3)
            .map(entry -> UserAccord.builder()
                    .user(user)
                    .name(entry.getKey())
                    .score(entry.getValue())
                    .build()
                )
            .collect(Collectors.toList());
    }

    private double convert(int score){
        return score * weights[score-1];
    }

}
