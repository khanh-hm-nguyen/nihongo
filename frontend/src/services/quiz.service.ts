import { QuizResponse, AnswerRequest, AnswerResponse } from "@/types/quiz";
import { handleResponse } from "@/utils/resHandler";

import { QuizType } from "@/types/quiz";

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL;


export const quizService = {

  // check quiz answer
  checkQuiz: async (request: AnswerRequest): Promise<AnswerResponse> => {
    const res = await fetch(`${API_BASE_URL}/check`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(request),
    });
    return handleResponse(res);
  },

  // get quiz questions by type
  fetchAllQuestions: async (type: QuizType): Promise<QuizResponse[]> => {
   const res = await fetch(`${API_BASE_URL}/all?type=${type}`, { cache: 'no-store' });
   return handleResponse(res);
  },
};
