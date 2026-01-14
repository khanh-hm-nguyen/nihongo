"use client";

import { useParams, useRouter } from "next/navigation";
import QuizCard from "@/components/QuizCard";
import { QuizType } from "@/types/quiz";

const QuizPage = () => {
  const params = useParams();
  const router = useRouter();

  const typeSlug = params.type as string;

  const quizType: QuizType = 
    typeSlug.toUpperCase() === "KATAKANA" ? "KATAKANA" : "HIRAGANA";

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-4">
      <QuizCard 
        quizType={quizType} 
        onBack={() => router.push('/')} // Go back to Home
      />
    </div>
  )
}

export default QuizPage