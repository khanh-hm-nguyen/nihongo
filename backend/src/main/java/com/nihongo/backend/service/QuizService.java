package com.nihongo.backend.service;

import com.nihongo.backend.dto.*;
import com.nihongo.backend.model.Hiragana;
import com.nihongo.backend.repository.HiraganaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final HiraganaRepository hiraganaRepository;
    private final Random random = new Random();

    public QuizResponseDTO generateQuiz() {
        long count = hiraganaRepository.count();
        if (count == 0) throw new RuntimeException("Database is empty!");

        // pick a random ID for the correct answer
        long randomId = random.nextLong(count) + 1;
        Hiragana correct = hiraganaRepository.findById(randomId)
                .orElseThrow(() -> new RuntimeException("Character not found"));

        // fetch 3 random wrong options
        List<Hiragana> distractors = hiraganaRepository.findRandomDistractors(correct.getId());

        List<String> options = new ArrayList<>();
        options.add(correct.getRomaji());
        distractors.forEach(d -> options.add(d.getRomaji()));
        Collections.shuffle(options);

        return QuizResponseDTO.builder()
                .questionId(correct.getId())
                .character(correct.getCharacter())
                .options(options)
                .build();


    }

    public AnswerResponseDTO checkAnswer(AnswerRequestDTO request) {
        Hiragana correctChar = hiraganaRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Invalid Question ID"));

        boolean isCorrect = correctChar.getRomaji().equalsIgnoreCase(request.getSelectedRomaji());

        return AnswerResponseDTO.builder()
                .correct(isCorrect)
                .correctRomaji(correctChar.getRomaji())
                .message(isCorrect ? "Correct! Well done." : "Oops! The correct answer was " + correctChar.getRomaji())
                .build();
    }
}
