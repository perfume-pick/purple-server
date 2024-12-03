package com.pikachu.purple.application.perfume.util;

import com.pikachu.purple.domain.review.StarRating;
import com.pikachu.purple.domain.user.User;
import com.pikachu.purple.domain.user.UserAccord;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class UserAccordRecommender {

    private static final double[] WEIGHTS = {-0.9, -0.7, 0.0, 0.8, 1.0};

    public List<UserAccord> recommend(
        User user,
        List<StarRating> starRatings
    ) {
        return recommendFromStarRatings(user, starRatings);
    }

    public List<UserAccord> recommend(
        User user,
        StarRating starRating
    ) {
        return recommendFromStarRatings(user, List.of(starRating));
    }

    private List<UserAccord> recommendFromStarRatings(
        User user,
        List<StarRating> starRatings
    ) {
        Map<String, Double> accordScores = calculateAccordScores(starRatings);

        return accordScores.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .map(entry -> UserAccord.builder()
                .user(user)
                .name(entry.getKey())
                .score(entry.getValue())
                .build()
            )
            .collect(Collectors.toList());
    }

    private Map<String, Double> calculateAccordScores(
        List<StarRating> starRatings
    ) {
        return starRatings.stream()
            .flatMap(this::extractAccordScores)
            .collect(
                Collectors.groupingBy(
                    AccordScore::name,
                    Collectors.summingDouble(AccordScore::score)
                )
            );
    }

    private Stream<AccordScore> extractAccordScores(StarRating starRating) {
        double weightedScore = convert(starRating.getScore());

        return starRating.getPerfume().getAccords().stream()
            .map(accord -> new AccordScore(accord.getName(), weightedScore));
    }

    private double convert(int score) {
        return WEIGHTS[score - 1];
    }

    private record AccordScore(String name, double score) {}

}
