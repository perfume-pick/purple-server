package com.pikachu.purple.domain.review;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReviewEvaluation {

    private final String DELIMITER = ":";
    private final Map<String, List<EvaluationOptionType>> optionData;
    private final Map<Long, List<EvaluationFieldType>> fieldData;

    public ReviewEvaluation() {
        this.fieldData = new HashMap<>();
        this.optionData = new HashMap<>();
    }

    private String buildOptionKey(Long reviewId, EvaluationFieldType field) {
        return reviewId + DELIMITER + field.getCode();
    }

    private boolean containsKey(Long reviewId) {
        return this.fieldData.containsKey(reviewId);
    }

    private boolean containsKey(Long reviewId, EvaluationFieldType field) {
        String optionKey = buildOptionKey(reviewId, field);
        return this.optionData.containsKey(optionKey);
    }

    private void add(Long reviewId) {
        this.fieldData.put(reviewId, new ArrayList<>());
    }

    private void add(
        Long reviewId,
        EvaluationFieldType field
    ) {
        if (!containsKey(reviewId)) {
            add(reviewId);
        }
        this.fieldData.get(reviewId).add(field);

        String optionKey = buildOptionKey(reviewId, field);
        this.optionData.put(optionKey, new ArrayList<>());
    }

    public void add(
        Long reviewId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        if (!containsKey(reviewId, field)) {
            add(reviewId, field);
        }
        String optionKey = buildOptionKey(reviewId, field);
        this.optionData.get(optionKey).add(option);
    }

    public void add(
        Long reviewId,
        EvaluationFieldType field,
        List<EvaluationOptionType> options
    ) {
        if (!containsKey(reviewId, field)) {
            add(reviewId, field);
        }
        String optionKey = buildOptionKey(reviewId, field);
        this.optionData.get(optionKey).addAll(options);
    }

    public Set<Long> getReviewIdSet() {
        return this.fieldData.keySet();
    }

    public List<EvaluationFieldType> getFields(Long reviewId) {
        return this.fieldData.get(reviewId);
    }

    public List<EvaluationOptionType> getOptions(Long reviewId, EvaluationFieldType field) {
        String optionKey = buildOptionKey(reviewId, field);
        return this.optionData.get(optionKey);
    }

}
