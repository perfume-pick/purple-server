package com.pikachu.purple.infrastructure.persistence.accord;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "accord")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccordJpaEntity {

    @Id
    @Column(name = "accord_name")
    private String name;

}

