package com.pikachu.purple.domain.review;

import static com.pikachu.purple.util.StringUtil.DELIMITER;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReviewEvaluation {

    private final Map<Long, List<EvaluationFieldType>> reviewFieldsMap;
    private final Map<String, List<EvaluationOptionType>> fieldOptionsMap;

    public ReviewEvaluation() {
        this.reviewFieldsMap = new HashMap<>();
        this.fieldOptionsMap = new HashMap<>();
    }

    private String buildFieldOptionsKey(
        Long reviewId,
        EvaluationFieldType field
    ) {
        return reviewId + DELIMITER + field.getCode();
    }

    private boolean containsKey(Long reviewId) {
        return this.reviewFieldsMap.containsKey(reviewId);
    }

    private boolean containsKey(
        Long reviewId,
        EvaluationFieldType field
    ) {
        String fieldOptionsKey = buildFieldOptionsKey(
            reviewId,
            field
        );
        return this.fieldOptionsMap.containsKey(fieldOptionsKey);
    }

    private void add(Long reviewId) {
        this.reviewFieldsMap.put(
            reviewId,
            new ArrayList<>()
        );
    }

    private void add(
        Long reviewId,
        EvaluationFieldType field
    ) {
        if (!containsKey(reviewId)) {
            add(reviewId);
        }
        this.reviewFieldsMap.get(reviewId).add(field);

        String fieldOptionsKey = buildFieldOptionsKey(
            reviewId,
            field
        );
        this.fieldOptionsMap.put(
            fieldOptionsKey,
            new ArrayList<>()
        );
    }

    public void add(
        Long reviewId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        if (!containsKey(reviewId, field)) {
            add(reviewId, field);
        }
        String fieldOptionsKey = buildFieldOptionsKey(
            reviewId,
            field
        );
        this.fieldOptionsMap.get(fieldOptionsKey).add(option);
    }

    public void add(
        Long reviewId,
        EvaluationFieldType field,
        List<EvaluationOptionType> options
    ) {
        if (!containsKey(reviewId, field)) {
            add(reviewId, field);
        }
        String fieldOptionsKey = buildFieldOptionsKey(
            reviewId,
            field
        );
        this.fieldOptionsMap.get(fieldOptionsKey).addAll(options);
    }

    public Set<Long> getReviewIdSet() {
        return this.reviewFieldsMap.keySet();
    }

    public List<EvaluationFieldType> getFields(Long reviewId) {
        return this.reviewFieldsMap.getOrDefault(
            reviewId,
            new ArrayList<>()
        );
    }

    public List<EvaluationOptionType> getOptions(
        Long reviewId,
        EvaluationFieldType field
    ) {
        String fieldOptionsKey = buildFieldOptionsKey(
            reviewId,
            field
        );
        return this.fieldOptionsMap.getOrDefault(
            fieldOptionsKey,
            new ArrayList<>()
        );
    }

}
