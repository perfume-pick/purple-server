package com.pikachu.purple.domain.perfume;

import com.pikachu.purple.domain.statistic.EvaluationStatistic;
import com.pikachu.purple.domain.statistic.StarRatingStatistic;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {

    private Long id;
    private String name;
    private String imageUrl;
    private double averageScore;
    private Brand brand;
    private List<PerfumeAccord> accords;
    private List<Note> notes;
    private FragranticaEvaluation fragranticaEvaluation;
    private EvaluationStatistic evaluationStatistic;
    private StarRatingStatistic starRatingStatistic;

}
