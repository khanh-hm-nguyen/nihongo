package com.nihongo.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponseDTO {
    private boolean correct;
    private String correctRomaji;
    private String message;
}
