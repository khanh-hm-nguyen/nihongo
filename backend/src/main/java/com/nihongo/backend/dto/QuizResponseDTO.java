package com.nihongo.backend.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class QuizResponseDTO {
    private Long questionId;
    private String character;
    private List<String> options;
}

