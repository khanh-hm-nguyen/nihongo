import { QuizResponse, AnswerRequest, AnswerResponse } from "@/types/quiz";
import { handleResponse } from "@/utils/resHandler";
const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL;

export const quizService = {
  fetchQuiz: async (): Promise<QuizResponse> => {
    const res = await fetch(`${API_BASE_URL}/quiz`);
    return handleResponse(res);
  },

  checkQuiz: async (request: AnswerRequest): Promise<AnswerResponse> => {
    const res = await fetch(`${API_BASE_URL}/check`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(request),
    });
    return handleResponse(res);
  },

  fetchAllQuestions: async (): Promise<QuizResponse[]> => {
    const res = await fetch(`${API_BASE_URL}/all`, { cache: "no-store" });
    if (!res.ok) throw new Error("Failed to fetch questions");
    return res.json();
  },
};
