package com.pikachu.purple.infrastructure.persistence.note.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "note")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoteJpaEntity {

    @Id
    @Column(name = "name")
    private String noteName;

}
