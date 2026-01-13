package com.nihongo.backend.controller;

import com.nihongo.backend.dto.*;
import com.nihongo.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hiragana")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz")
    public ResponseEntity<QuizResponseDTO> getQuiz() {
        return ResponseEntity.ok(quizService.generateQuiz());
    }

    @PostMapping("/check")
    public ResponseEntity<AnswerResponseDTO> checkAnswer(@RequestBody AnswerRequestDTO request) {
        return ResponseEntity.ok(quizService.checkAnswer(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizResponseDTO>> getAllQuestions() {
        return ResponseEntity.ok(quizService.getAllQuestions());
    }
}
