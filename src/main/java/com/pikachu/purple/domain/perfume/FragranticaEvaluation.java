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

    private boolean containsKey(
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        String optionVotesKey = buildOptionVotesKey(
            field,
            option
        );
        return this.optionVotesMap.containsKey(optionVotesKey);
    }

    private void add(EvaluationFieldType field) {
        this.fieldOptionsMap.put(
            field,
            new ArrayList<>()
        );
    }

    private void add(
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        if (!containsKey(field)) {
            add(field);
        }
        this.fieldOptionsMap.get(field).add(option);

        String optionVotesKey = buildOptionVotesKey(
            field,
            option
        );
        this.optionVotesMap.put(optionVotesKey, 0);
    }

    public void set(
        EvaluationFieldType field,
        EvaluationOptionType option,
        int votes
    ) {
        if (!containsKey(field, option)) {
            add(field, option);
        }

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

    public int getVotes(
        EvaluationFieldType field,
        EvaluationOptionType option
    ) {
        String optionVotesKey = buildOptionVotesKey(
            field,
            option
        );
        return this.optionVotesMap.getOrDefault(
            optionVotesKey,
            0
        );
    }

}
