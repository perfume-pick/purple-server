package com.pikachu.purple.infrastructure.persistence.perfume.entity.id;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class PerfumeNoteId implements Serializable {

    private Long perfumeId;
    private String name;

}
