package com.nihongo.backend.config;

import com.nihongo.backend.model.Hiragana;
import com.nihongo.backend.repository.HiraganaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(HiraganaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Hiragana> allHiragana = List.of(
                        // A
                        new Hiragana(null, "あ", "a"), new Hiragana(null, "い", "i"),
                        new Hiragana(null, "う", "u"), new Hiragana(null, "え", "e"), new Hiragana(null, "お", "o"),
                        // Ka
                        new Hiragana(null, "か", "ka"), new Hiragana(null, "き", "ki"),
                        new Hiragana(null, "く", "ku"), new Hiragana(null, "け", "ke"), new Hiragana(null, "こ", "ko"),
                        // Sa
                        new Hiragana(null, "さ", "sa"), new Hiragana(null, "し", "shi"),
                        new Hiragana(null, "す", "su"), new Hiragana(null, "せ", "se"), new Hiragana(null, "そ", "so"),
                        // Ta
                        new Hiragana(null, "た", "ta"), new Hiragana(null, "ち", "chi"),
                        new Hiragana(null, "つ", "tsu"), new Hiragana(null, "て", "te"), new Hiragana(null, "と", "to"),
                        // Na
                        new Hiragana(null, "な", "na"), new Hiragana(null, "に", "ni"),
                        new Hiragana(null, "ぬ", "nu"), new Hiragana(null, "ね", "ne"), new Hiragana(null, "の", "no"),
                        // Ha
                        new Hiragana(null, "は", "ha"), new Hiragana(null, "ひ", "hi"),
                        new Hiragana(null, "ふ", "fu"), new Hiragana(null, "へ", "he"), new Hiragana(null, "ほ", "ho"),
                        // Ma
                        new Hiragana(null, "ま", "ma"), new Hiragana(null, "み", "mi"),
                        new Hiragana(null, "む", "mu"), new Hiragana(null, "め", "me"), new Hiragana(null, "も", "mo"),
                        // Ya
                        new Hiragana(null, "や", "ya"), new Hiragana(null, "ゆ", "yu"), new Hiragana(null, "よ", "yo"),
                        // Ra
                        new Hiragana(null, "ら", "ra"), new Hiragana(null, "り", "ri"),
                        new Hiragana(null, "る", "ru"), new Hiragana(null, "れ", "re"), new Hiragana(null, "ろ", "ro"),
                        // Wa / N
                        new Hiragana(null, "わ", "wa"), new Hiragana(null, "を", "wo"), new Hiragana(null, "ん", "n")
                );

                repository.saveAll(allHiragana);
                System.out.println("Database seeded with all 46 Hiragana characters!");
            }
        };
    }
}