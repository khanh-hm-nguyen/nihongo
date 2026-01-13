package com.nihongo.backend.dto;

import lombok.Data;

@Data
public class AnswerRequestDTO {
    private Long questionId;
    private String selectedRomaji;
}
