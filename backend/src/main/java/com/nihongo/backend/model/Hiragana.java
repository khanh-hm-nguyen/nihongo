package com.nihongo.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Hiragana Table to store master list of Hiragana characters
 */
@Entity
@Table(name = "hiragana_characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hiragana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String character; // e.g., "あ"

    @Column(nullable = false)
    private String romaji;
}
