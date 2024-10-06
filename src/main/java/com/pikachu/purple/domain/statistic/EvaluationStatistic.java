package com.pikachu.purple.domain.statistic;

import static com.pikachu.purple.util.StringUtil.DELIMITER;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationStatistic {

    private final Map<Long, List<EvaluationFieldType>> perfumeFieldsMap;
    private final Map<String, List<EvaluationOptionType>> fieldOptionsMap;
    private final Map<String, Integer> optionVotesMap;

    public EvaluationStatistic() {
        this.perfumeFieldsMap = new HashMap<>();
        this.fieldOptionsMap = new HashMap<>();
        this.optionVotesMap = new HashMap<>();
    }

    private String buildFieldOptionsKey(
        Long perfumeId,
        EvaluationFieldType field
    ) {
        return perfumeId + DELIMITER + field.getCode();
    }

    private String buildOptionVotesKey(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        return buildFieldOptionsKey(
            perfumeId,
            field
        ) + DELIMITER + option.getCode();
    }

    private boolean containsKey(Long perfumeId) {
        return this.perfumeFieldsMap.containsKey(perfumeId);
    }

    private boolean containsKey(
        Long perfumeId,
        EvaluationFieldType field
    ) {
        String fieldOptionsKey = buildFieldOptionsKey(
            perfumeId,
            field
        );
        return this.fieldOptionsMap.containsKey(fieldOptionsKey);
    }

    private void add(Long perfumeId) {
        this.perfumeFieldsMap.put(
            perfumeId,
            new ArrayList<>()
        );
    }

    private void add(
        Long perfumeId,
        EvaluationFieldType field
    ) {
        if (!containsKey(perfumeId)) {
            add(perfumeId);
        }
        this.perfumeFieldsMap.get(perfumeId).add(field);

        String fieldOptionsKey = buildFieldOptionsKey(
            perfumeId,
            field
        );
        this.fieldOptionsMap.put(
            fieldOptionsKey,
            new ArrayList<>()
        );
    }

    public void add(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option,
        int votes
    ) {
        if (!containsKey(perfumeId, field)) {
            add(perfumeId, field);
        }
        String fieldOptionsKey = buildFieldOptionsKey(
            perfumeId,
            field
        );
        this.fieldOptionsMap.get(fieldOptionsKey).add(option);

        String optionVotesKey = buildOptionVotesKey(
            perfumeId,
            field,
            option
        );
        this.optionVotesMap.put(optionVotesKey, votes);
    }

    public Set<Long> getPerfumeIdSet() {
        return this.perfumeFieldsMap.keySet();
    }

    public List<EvaluationFieldType> getFields(Long perfumeId) {
        return this.perfumeFieldsMap.getOrDefault(
            perfumeId,
            new ArrayList<>()
        );
    }

    public List<EvaluationOptionType> getOptions(
        Long perfumeId,
        EvaluationFieldType field
    ) {
        String fieldOptionsKey = buildFieldOptionsKey(
            perfumeId,
            field
        );
        return this.fieldOptionsMap.getOrDefault(
            fieldOptionsKey,
            new ArrayList<>()
        );
    }

    public int getVotes(
        Long perfumeId,
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        String optionVotesKey = buildOptionVotesKey(
            perfumeId,
            field,
            option
        );
        return this.optionVotesMap.getOrDefault(
            optionVotesKey,
            0
        );
    }

}
