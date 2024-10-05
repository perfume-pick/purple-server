package com.pikachu.purple.domain.perfume;

import static com.pikachu.purple.util.StringUtil.DELIMITER;

import com.pikachu.purple.domain.evaluation.enums.EvaluationFieldType;
import com.pikachu.purple.domain.evaluation.enums.EvaluationOptionType;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FragranticaEvaluation {

    private final Map<EvaluationFieldType, List<EvaluationOptionType>> fieldOptionsMap;
    private final Map<String, Integer> optionVotesMap;

    public FragranticaEvaluation() {
        this.fieldOptionsMap = new EnumMap<>(EvaluationFieldType.class);
        this.optionVotesMap = new HashMap<>();
    }

    private String buildOptionVotesKey(
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        return field.getCode() + DELIMITER + option.getCode();
    }

    private boolean containsKey(EvaluationFieldType field) {
        return this.fieldOptionsMap.containsKey(field);
    }

    private void add(EvaluationFieldType field) {
        this.fieldOptionsMap.put(
            field,
            new ArrayList<>()
        );
    }

    public void add(
        EvaluationFieldType field,
        EvaluationOptionType option,
        Integer votes
    ) {
        if (!containsKey(field)) {
            add(field);
        }
        this.fieldOptionsMap.get(field).add(option);

        String optionVotesKey = buildOptionVotesKey(
            field,
            option
        );
        this.optionVotesMap.put(optionVotesKey, votes);
    }

    public Set<EvaluationFieldType> getFieldSet() {
        return this.fieldOptionsMap.keySet();
    }

    public List<EvaluationOptionType> getOptions(EvaluationFieldType field) {
        return this.fieldOptionsMap.getOrDefault(
            field,
            new ArrayList<>()
        );
    }

    public int getVotes(EvaluationFieldType field, EvaluationOptionType option) {
        String optionVotesKey = buildOptionVotesKey(field, option);
        return this.optionVotesMap.getOrDefault(
            optionVotesKey,
            0
        );
    }

}
