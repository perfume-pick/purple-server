package com.pikachu.purple.infrastructure.persistence.user.entity.id;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class UserAccordId implements Serializable {

    private Long userJpaEntity;
    private String accordJpaEntity;

}
