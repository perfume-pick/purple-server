package com.pikachu.purple.infrastructure.persistence.accord.converter;

import static com.pikachu.purple.bootstrap.common.exception.BusinessException.AccordNotFoundException;

import com.pikachu.purple.domain.accord.enums.Accord;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.EnumSet;

@Converter(autoApply = true)
public class AccordConverter implements AttributeConverter<Accord, String> {

    @Override
    public String convertToDatabaseColumn(Accord accord) {
        return accord.name().toLowerCase().replace("_", " ");
    }

    @Override
    public Accord convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(Accord.class).stream()
            .filter(e -> e.name().equals(dbData.toUpperCase().replace(" ", "_")))
            .findAny()
            .orElseThrow(() -> AccordNotFoundException);
    }

}

