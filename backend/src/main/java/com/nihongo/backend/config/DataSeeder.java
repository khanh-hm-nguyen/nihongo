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
                repository.saveAll(List.of(
                        new Hiragana(null, "あ", "a"), new Hiragana(null, "い", "i"),
                        new Hiragana(null, "う", "u"), new Hiragana(null, "え", "e"),
                        new Hiragana(null, "お", "o"), new Hiragana(null, "か", "ka"),
                        new Hiragana(null, "き", "ki"), new Hiragana(null, "く", "ku")

                ));
                System.out.println("Database seeded with Hiragana!");
            }
        };
    }
}