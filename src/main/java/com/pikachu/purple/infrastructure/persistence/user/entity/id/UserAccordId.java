package com.pikachu.purple.infrastructure.persistence.user.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class UserAccordId implements Serializable {

    private Long userJpaEntity;
    private String accordJpaEntity;

}
