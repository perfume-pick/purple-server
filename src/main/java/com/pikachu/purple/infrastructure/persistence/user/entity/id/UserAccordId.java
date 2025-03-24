package com.pikachu.purple.infrastructure.persistence.user.entity.id;

import com.pikachu.purple.domain.accord.enums.Accord;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
public class UserAccordId implements Serializable {

    private Long userId;
    private Accord accord;

}
