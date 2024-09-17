package com.pikachu.purple.infrastructure.persistence.accord;

import com.pikachu.purple.domain.accord.Accord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "accord")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AccordJpaEntity {

    @Id
    @Column(name = "accord_name")
    private String name;

    public static AccordJpaEntity toJpaEntity(Accord domain) {
        return AccordJpaEntity.builder()
            .name(domain.getName())
            .build();
    }

}

