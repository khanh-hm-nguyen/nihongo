package com.nihongo.backend.service;

import com.nihongo.backend.dto.*;
import com.nihongo.backend.model.CharacterType;
import com.nihongo.backend.model.JapaneseCharacter;
import com.nihongo.backend.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final CharacterRepository characterRepository;
    private final Random random = new Random();


    public AnswerResponseDTO checkAnswer(AnswerRequestDTO request) {
        JapaneseCharacter correctChar = characterRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Invalid Question ID"));

        boolean isCorrect = correctChar.getRomaji().equalsIgnoreCase(request.getSelectedRomaji());

        return AnswerResponseDTO.builder()
                .correct(isCorrect)
                .correctRomaji(correctChar.getRomaji())
                .message(isCorrect ? "Correct! Well done." : "Oops! The correct answer was " + correctChar.getRomaji())
                .build();
    }


    public List<QuizResponseDTO> getAllQuestions(CharacterType type) {
        // 1. Fetch only the characters of the requested type
        List<JapaneseCharacter> allChars = characterRepository.findByType(type);

        if (allChars.isEmpty()) throw new RuntimeException("Database empty for type: " + type);

        // 2. Generate questions for the list
        return allChars.stream().map(correct -> {

            // Create options list
            List<String> options = new ArrayList<>();
            options.add(correct.getRomaji());

            // Create a pool of distractors (wrong answers) from the SAME list
            List<JapaneseCharacter> distractors = new ArrayList<>(allChars);
            distractors.remove(correct); // Remove the correct answer
            Collections.shuffle(distractors); // Shuffle to randomize

            // Take top 3 wrong answers
            distractors.stream()
                    .limit(3)
                    .forEach(d -> options.add(d.getRomaji()));

            Collections.shuffle(options); // Shuffle options so correct answer isn't always first

            return QuizResponseDTO.builder()
                    .questionId(correct.getId())
                    .character(correct.getCharacter())
                    .options(options)
                    .build();
        }).toList();
    }


}
