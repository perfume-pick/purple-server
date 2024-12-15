package com.pikachu.purple.infrastructure.persistence.accord.entity;

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

    @Column(name = "accord_korean_name")
    private String koreanName;

    public static AccordJpaEntity toJpaEntity(Accord domain) {
        return AccordJpaEntity.builder()
            .name(domain.getName())
            .koreanName(domain.getKoreanName())
            .build();
    }


}

