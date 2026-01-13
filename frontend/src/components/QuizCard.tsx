"use client"
import { useState, useEffect } from "react";
import { quizService } from "@/services/quiz.service";
import { QuizResponse } from "@/types/quiz";

const QuizCard = () => {
  const [question, setQuestion] = useState<QuizResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState<string | null>(null);
  const [result, setResult] = useState<{ correct: boolean; message: string } | null>(null);
  const [score, setScore] = useState(0);

  // Load initial question
  useEffect(() => {
    loadNewQuestion();
  }, []);

  const loadNewQuestion = async () => {
    setLoading(true);
    setResult(null);
    setSelected(null);
    try {
      const data = await quizService.fetchQuiz();
      setQuestion(data);
    } catch (error) {
      console.error("Error loading quiz:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleOptionClick = async (option: string) => {
    if (result || !question) return; // Prevent double clicking

    setSelected(option);
    
    try {
      const res = await quizService.checkQuiz({
        questionId: question.questionId,
        selectedRomaji: option,
      });

      setResult({ correct: res.correct, message: res.message });
      if (res.correct) setScore((prev) => prev + 1);
      
    } catch (error) {
      console.error("Error checking answer:", error);
    }
  };

  if (loading) return <div className="text-xl animate-pulse">Loading Hiragana...</div>;
  if (!question) return <div className="text-red-500">Failed to load quiz. Check backend!</div>;
  return (
    <div className="w-full max-w-md p-6 bg-white rounded-2xl shadow-xl border border-gray-100">
      
      {/* Header / Score */}
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-gray-500 font-medium">Hiragana Quiz</h2>
        <span className="bg-indigo-100 text-indigo-700 px-3 py-1 rounded-full text-sm font-bold">
          Score: {score}
        </span>
      </div>

      {/* The Character Card */}
      <div className="flex justify-center items-center h-40 mb-8 bg-gray-50 rounded-xl border-2 border-dashed border-gray-200">
        <span className="text-8xl font-black text-gray-800">{question.character}</span>
      </div>

      {/* Options Grid */}
      <div className="grid grid-cols-2 gap-4 mb-6">
        {question.options.map((option) => {
          // Dynamic styling based on state
          let btnStyle = "bg-white hover:bg-indigo-50 border-gray-200 text-gray-700";
          
          if (selected === option) {
             if (result?.correct) btnStyle = "bg-green-500 border-green-600 text-white";
             else if (result && !result.correct) btnStyle = "bg-red-500 border-red-600 text-white";
             else btnStyle = "bg-indigo-500 border-indigo-600 text-white"; // Loading state style
          } else if (result && option !== selected) {
             btnStyle = "bg-gray-100 text-gray-400 opacity-50 cursor-not-allowed";
          }

          return (
            <button
              key={option}
              onClick={() => handleOptionClick(option)}
              disabled={!!result}
              className={`py-4 text-xl font-bold rounded-xl border-2 transition-all duration-200 shadow-sm ${btnStyle}`}
            >
              {option}
            </button>
          );
        })}
      </div>

      {/* Result & Next Button */}
      {result && (
        <div className={`p-4 rounded-lg mb-4 text-center ${result.correct ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"}`}>
          <p className="font-bold mb-3">{result.message}</p>
          <button
            onClick={loadNewQuestion}
            className="w-full py-2 bg-gray-800 text-white rounded-lg hover:bg-black transition-colors"
          >
            Next Question →
          </button>
        </div>
      )}
    </div>
  )
}

export default QuizCard