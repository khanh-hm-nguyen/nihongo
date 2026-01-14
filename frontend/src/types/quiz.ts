export interface AnswerRequest {
    questionId: number;
    selectedRomaji: string
}

export interface AnswerResponse {
    correct: boolean;
    correctRomaji: boolean;
    message: string
}

export interface QuizResponse {
    questionId: number;
    character: string;
    options: string[]
}

export type QuizType = "HIRAGANA" | "KATAKANA";