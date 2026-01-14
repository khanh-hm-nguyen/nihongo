package com.nihongo.backend.config;

import com.nihongo.backend.model.CharacterType;
import com.nihongo.backend.model.JapaneseCharacter;
import com.nihongo.backend.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(CharacterRepository repository) {
        return args -> {
            // Only seed if the database is empty
            if (repository.count() == 0) {
                List<JapaneseCharacter> characters = new ArrayList<>();

                // --- HIRAGANA (46 Basic Characters) ---
                characters.addAll(List.of(
                        // Vowels
                        create("あ", "a", CharacterType.HIRAGANA), create("い", "i", CharacterType.HIRAGANA),
                        create("う", "u", CharacterType.HIRAGANA), create("え", "e", CharacterType.HIRAGANA), create("お", "o", CharacterType.HIRAGANA),
                        // K
                        create("か", "ka", CharacterType.HIRAGANA), create("き", "ki", CharacterType.HIRAGANA),
                        create("く", "ku", CharacterType.HIRAGANA), create("け", "ke", CharacterType.HIRAGANA), create("こ", "ko", CharacterType.HIRAGANA),
                        // S
                        create("さ", "sa", CharacterType.HIRAGANA), create("し", "shi", CharacterType.HIRAGANA),
                        create("す", "su", CharacterType.HIRAGANA), create("せ", "se", CharacterType.HIRAGANA), create("そ", "so", CharacterType.HIRAGANA),
                        // T
                        create("た", "ta", CharacterType.HIRAGANA), create("ち", "chi", CharacterType.HIRAGANA),
                        create("つ", "tsu", CharacterType.HIRAGANA), create("て", "te", CharacterType.HIRAGANA), create("と", "to", CharacterType.HIRAGANA),
                        // N
                        create("な", "na", CharacterType.HIRAGANA), create("に", "ni", CharacterType.HIRAGANA),
                        create("ぬ", "nu", CharacterType.HIRAGANA), create("ね", "ne", CharacterType.HIRAGANA), create("の", "no", CharacterType.HIRAGANA),
                        // H
                        create("は", "ha", CharacterType.HIRAGANA), create("ひ", "hi", CharacterType.HIRAGANA),
                        create("ふ", "fu", CharacterType.HIRAGANA), create("へ", "he", CharacterType.HIRAGANA), create("ほ", "ho", CharacterType.HIRAGANA),
                        // M
                        create("ま", "ma", CharacterType.HIRAGANA), create("み", "mi", CharacterType.HIRAGANA),
                        create("む", "mu", CharacterType.HIRAGANA), create("め", "me", CharacterType.HIRAGANA), create("も", "mo", CharacterType.HIRAGANA),
                        // Y
                        create("や", "ya", CharacterType.HIRAGANA), create("ゆ", "yu", CharacterType.HIRAGANA), create("よ", "yo", CharacterType.HIRAGANA),
                        // R
                        create("ら", "ra", CharacterType.HIRAGANA), create("り", "ri", CharacterType.HIRAGANA),
                        create("る", "ru", CharacterType.HIRAGANA), create("れ", "re", CharacterType.HIRAGANA), create("ろ", "ro", CharacterType.HIRAGANA),
                        // W / N
                        create("わ", "wa", CharacterType.HIRAGANA), create("を", "wo", CharacterType.HIRAGANA), create("ん", "n", CharacterType.HIRAGANA)
                ));

                // --- KATAKANA (46 Basic Characters) ---
                characters.addAll(List.of(
                        // Vowels
                        create("ア", "a", CharacterType.KATAKANA), create("イ", "i", CharacterType.KATAKANA),
                        create("ウ", "u", CharacterType.KATAKANA), create("エ", "e", CharacterType.KATAKANA), create("オ", "o", CharacterType.KATAKANA),
                        // K
                        create("カ", "ka", CharacterType.KATAKANA), create("キ", "ki", CharacterType.KATAKANA),
                        create("ク", "ku", CharacterType.KATAKANA), create("ケ", "ke", CharacterType.KATAKANA), create("コ", "ko", CharacterType.KATAKANA),
                        // S
                        create("サ", "sa", CharacterType.KATAKANA), create("シ", "shi", CharacterType.KATAKANA),
                        create("ス", "su", CharacterType.KATAKANA), create("セ", "se", CharacterType.KATAKANA), create("ソ", "so", CharacterType.KATAKANA),
                        // T
                        create("タ", "ta", CharacterType.KATAKANA), create("チ", "chi", CharacterType.KATAKANA),
                        create("ツ", "tsu", CharacterType.KATAKANA), create("テ", "te", CharacterType.KATAKANA), create("ト", "to", CharacterType.KATAKANA),
                        // N
                        create("ナ", "na", CharacterType.KATAKANA), create("ニ", "ni", CharacterType.KATAKANA),
                        create("ヌ", "nu", CharacterType.KATAKANA), create("ネ", "ne", CharacterType.KATAKANA), create("ノ", "no", CharacterType.KATAKANA),
                        // H
                        create("ハ", "ha", CharacterType.KATAKANA), create("ヒ", "hi", CharacterType.KATAKANA),
                        create("フ", "fu", CharacterType.KATAKANA), create("ヘ", "he", CharacterType.KATAKANA), create("ホ", "ho", CharacterType.KATAKANA),
                        // M
                        create("マ", "ma", CharacterType.KATAKANA), create("ミ", "mi", CharacterType.KATAKANA),
                        create("ム", "mu", CharacterType.KATAKANA), create("メ", "me", CharacterType.KATAKANA), create("モ", "mo", CharacterType.KATAKANA),
                        // Y
                        create("ヤ", "ya", CharacterType.KATAKANA), create("ユ", "yu", CharacterType.KATAKANA), create("ヨ", "yo", CharacterType.KATAKANA),
                        // R
                        create("ラ", "ra", CharacterType.KATAKANA), create("リ", "ri", CharacterType.KATAKANA),
                        create("ル", "ru", CharacterType.KATAKANA), create("レ", "re", CharacterType.KATAKANA), create("ロ", "ro", CharacterType.KATAKANA),
                        // W / N
                        create("ワ", "wa", CharacterType.KATAKANA), create("ヲ", "wo", CharacterType.KATAKANA), create("ン", "n", CharacterType.KATAKANA)
                ));

                repository.saveAll(characters);
                System.out.println("Database seeded with " + characters.size() + " Japanese characters!");
            }
        };
    }

    // Helper method to make the code cleaner
    private JapaneseCharacter create(String character, String romaji, CharacterType type) {
        return new JapaneseCharacter(null, character, romaji, type);
    }
}