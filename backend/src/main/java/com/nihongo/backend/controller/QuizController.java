package com.nihongo.backend.controller;

import com.nihongo.backend.dto.*;
import com.nihongo.backend.model.CharacterType;
import com.nihongo.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;


    @PostMapping("/check")
    public ResponseEntity<AnswerResponseDTO> checkAnswer(@RequestBody AnswerRequestDTO request) {
        return ResponseEntity.ok(quizService.checkAnswer(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizResponseDTO>> getAllQuestions(@RequestParam CharacterType type) {
        return ResponseEntity.ok(quizService.getAllQuestions(type));
    }
}
