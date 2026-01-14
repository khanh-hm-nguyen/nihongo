"use client";

import { useState, useEffect } from "react";
import { QuizType } from "@/types/quiz";
import { quizService } from "@/services/quiz.service";
import { QuizResponse } from "@/types/quiz";

import { shuffleArray } from "@/utils";

// props to accept the selected type and a back function
interface QuizCardProps {
  quizType: QuizType;
  onBack: () => void;
}

const QuizCard = ({ quizType, onBack }: QuizCardProps) => {
  const [questions, setQuestions] = useState<QuizResponse[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState<string | null>(null);
  const [result, setResult] = useState<{ correct: boolean; message: string } | null>(null);
  const [score, setScore] = useState(0);
  const [gameMode, setGameMode] = useState<"SEQUENTIAL" | "RANDOM">("SEQUENTIAL");

  // Reload quiz whenever the 'quizType' prop changes
  useEffect(() => {
    initializeQuiz();
  }, [quizType]);

  const initializeQuiz = async (mode: "SEQUENTIAL" | "RANDOM" = "SEQUENTIAL") => {
    setLoading(true);
    try {
      // Pass the quizType to the API call
      let data = await quizService.fetchAllQuestions(quizType);
      
      if (mode === "RANDOM") {
        data = shuffleArray(data);
      }
      
      setQuestions(data);
      setCurrentIndex(0);
      setScore(0);
      setResult(null);
      setSelected(null);
      setGameMode(mode);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const currentQuestion = questions[currentIndex];
  const isFinished = !loading && questions.length > 0 && currentIndex >= questions.length;

  // ... (handleOptionClick and handleNext are unchanged) ...
  const handleOptionClick = async (option: string) => {
    if (result || !currentQuestion) return;
    setSelected(option);
    try {
      const res = await quizService.checkQuiz({
        questionId: currentQuestion.questionId,
        selectedRomaji: option,
      });
      setResult({ correct: res.correct, message: res.message });
      if (res.correct) setScore((prev) => prev + 1);
    } catch (err) { console.error(err); }
  };

  const handleNext = () => {
    setResult(null);
    setSelected(null);
    setCurrentIndex((prev) => prev + 1);
  };

  if (loading) return <div className="text-xl animate-pulse text-center mt-10">Loading {quizType}...</div>;

  // --- GAME OVER VIEW ---
  if (isFinished) {
    return (
      <div className="w-full max-w-md p-8 bg-white rounded-2xl shadow-xl text-center mt-8">
        <h2 className="text-3xl font-bold mb-4">Quiz Complete!</h2>
        <p className="text-xl mb-6">Score: <span className="font-bold text-indigo-600">{score}</span> / {questions.length}</p>
        <div className="flex gap-2 justify-center mb-4">
            <button onClick={() => initializeQuiz("SEQUENTIAL")} className="px-4 py-2 bg-indigo-600 text-white rounded-lg">Restart (Order)</button>
            <button onClick={() => initializeQuiz("RANDOM")} className="px-4 py-2 bg-pink-500 text-white rounded-lg">Restart (Random)</button>
        </div>
        <button onClick={onBack} className="text-gray-500 hover:text-black underline">Back to Menu</button>
      </div>
    );
  }

  // --- MAIN QUIZ VIEW ---
  return (
    <div className="w-full max-w-md p-6 bg-white rounded-2xl shadow-xl border border-gray-100 mt-4 relative">
      
      {/* Back Button */}
      <button onClick={onBack} className="absolute top-6 left-6 text-gray-400 hover:text-gray-600">
        ← Menu
      </button>

      {/* Header */}
      <div className="flex justify-end items-center mb-6 border-b border-gray-100 pb-4 h-8">
        <span className="bg-indigo-50 text-indigo-700 px-3 py-1 rounded-full text-xs font-bold">
          {quizType} • Score: {score}
        </span>
      </div>

      {/* Character */}
      <div className="flex justify-center items-center h-48 mb-8 bg-gray-50 rounded-2xl border-2 border-dashed border-gray-200">
        <span className="text-9xl font-black text-gray-800 pb-3">{currentQuestion?.character}</span>
      </div>

      {/* Options */}
      <div className="grid grid-cols-2 gap-4 mb-6">
        {currentQuestion?.options.map((option) => {
           // ... (Styling logic remains the same) ...
           let btnStyle = "bg-white hover:bg-indigo-50 border-gray-200 text-gray-700";
           if (selected === option) {
             if (result?.correct) btnStyle = "bg-green-500 border-green-600 text-white";
             else if (result && !result.correct) btnStyle = "bg-red-500 border-red-600 text-white";
             else btnStyle = "bg-indigo-600 text-white";
           } else if (result && option !== selected) {
             btnStyle = "bg-gray-50 opacity-50 cursor-not-allowed";
           }

           return (
             <button
               key={option}
               onClick={() => handleOptionClick(option)}
               disabled={!!result}
               className={`py-4 text-xl font-bold rounded-xl border-2 transition-all shadow-sm ${btnStyle}`}
             >
               {option}
             </button>
           );
        })}
      </div>

      {/* Next Button */}
      {result && (
        <button onClick={handleNext} className="w-full py-4 bg-gray-900 text-white font-bold rounded-xl hover:bg-black transition-all">
          {currentIndex + 1 === questions.length ? "Finish" : "Next →"}
        </button>
      )}
    </div>
  );
};

export default QuizCard;