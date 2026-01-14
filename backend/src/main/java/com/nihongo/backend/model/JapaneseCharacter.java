package com.nihongo.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "japanese_character") // Renamed table to be clear
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JapaneseCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String character;

    @Column(nullable = false)
    private String romaji;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CharacterType type;
}