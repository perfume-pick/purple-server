package com.pikachu.purple.infrastructure.persistence.review.converter;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.MoodNotFoundException;

import com.pikachu.purple.domain.review.enums.Mood;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;

@Converter(autoApply = true)
public class MoodConverter implements AttributeConverter<Mood, String> {

    @Override
    public String convertToDatabaseColumn(Mood mood) {
        return mood.getKoreanName();
    }

    @Override
    public Mood convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Mood.class).stream()
            .filter(e -> e.getKoreanName().equals(dbData))
            .findAny()
            .orElseThrow(() -> MoodNotFoundException);
    }

}
