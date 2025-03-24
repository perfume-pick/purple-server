package com.pikachu.purple.infrastructure.persistence.perfume.entity.id;

import com.pikachu.purple.domain.accord.enums.Accord;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class PerfumeAccordId implements Serializable {

    private Long perfumeId;
    private Accord accord;

}
