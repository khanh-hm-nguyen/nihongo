"use client"
import { useState, useEffect } from "react";
import { quizService } from "@/services/quiz.service";
import { QuizResponse } from "@/types/quiz";
import { shuffleArray } from "@/utils";


const QuizCard = () => {
  const [questions, setQuestions] = useState<QuizResponse[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState<string | null>(null);
  const [result, setResult] = useState<{ correct: boolean; message: string } | null>(null);
  const [score, setScore] = useState(0);
  const [gameMode, setGameMode] = useState<"SEQUENTIAL" | "RANDOM">("SEQUENTIAL");

  // Load questions on mount
  useEffect(() => {
    initializeQuiz();
  }, []);

  const initializeQuiz = async (mode: "SEQUENTIAL" | "RANDOM" = "SEQUENTIAL") => {
    setLoading(true);
    try {
      let data = await quizService.fetchAllQuestions();
      
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
      console.error("Failed to initialize quiz:", error);
    } finally {
      setLoading(false);
    }
  };

  const currentQuestion = questions[currentIndex];
  const isFinished = !loading && questions.length > 0 && currentIndex >= questions.length;

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
    } catch (err) {
      console.error(err);
    }
  };

  const handleNext = () => {
    setResult(null);
    setSelected(null);
    setCurrentIndex((prev) => prev + 1);
  };

  if (loading) return <div className="text-xl animate-pulse text-indigo-600 font-bold mt-10">Loading Quiz Data...</div>;

  // --- GAME OVER VIEW ---
  if (isFinished) {
    return (
      <div className="w-full max-w-md p-8 bg-white rounded-2xl shadow-xl text-center border border-gray-100 mt-8">
        <h2 className="text-3xl font-bold mb-4 text-gray-800">Quiz Complete!</h2>
        <div className="text-6xl mb-6">🎉</div>
        <p className="text-xl mb-8 text-gray-600">
          You scored <span className="font-bold text-indigo-600">{score}</span> out of <span className="font-bold">{questions.length}</span>
        </p>
        <div className="flex flex-col gap-3">
          <button 
            onClick={() => initializeQuiz("SEQUENTIAL")} 
            className="w-full py-3 bg-indigo-600 text-white font-semibold rounded-xl hover:bg-indigo-700 transition shadow-md"
          >
            Restart (A-Z Order)
          </button>
          <button 
            onClick={() => initializeQuiz("RANDOM")} 
            className="w-full py-3 bg-pink-500 text-white font-semibold rounded-xl hover:bg-pink-600 transition shadow-md"
          >
            Restart (Random Mode)
          </button>
        </div>
      </div>
    );
  }

  // --- QUIZ VIEW ---
  return (
    <div className="w-full max-w-md p-6 bg-white rounded-2xl shadow-xl border border-gray-100 mt-4">
      
      {/* Quiz Progress & Score Header */}
      <div className="flex justify-between items-center mb-6 border-b border-gray-100 pb-4">
        <div className="flex flex-col">
          <span className="text-[10px] font-black uppercase tracking-widest text-gray-400">
            {gameMode} MODE
          </span>
          <span className="text-sm font-bold text-gray-600">
            Question {currentIndex + 1} <span className="text-gray-300">/</span> {questions.length}
          </span>
        </div>
        <div className="bg-indigo-50 text-indigo-700 px-4 py-2 rounded-full text-sm font-bold border border-indigo-100">
          Score: {score}
        </div>
      </div>

      {/* Character Display */}
      <div className="flex justify-center items-center h-48 mb-8 bg-gray-50 rounded-2xl border-2 border-dashed border-gray-200">
        <span className="text-9xl font-black text-gray-800 pb-4">{currentQuestion?.character}</span>
      </div>

      {/* Options Grid */}
      <div className="grid grid-cols-2 gap-4 mb-6">
        {currentQuestion?.options.map((option) => {
          let btnStyle = "bg-white hover:bg-indigo-50 border-gray-200 text-gray-700 hover:border-indigo-200";
          
          if (selected === option) {
             if (result?.correct) btnStyle = "bg-green-500 border-green-600 text-white shadow-green-200";
             else if (result && !result.correct) btnStyle = "bg-red-500 border-red-600 text-white shadow-red-200";
             else btnStyle = "bg-indigo-600 border-indigo-700 text-white";
          } else if (result && option !== selected) {
             btnStyle = "bg-gray-50 text-gray-300 border-gray-100 opacity-50 cursor-not-allowed";
          }

          return (
            <button
              key={option}
              onClick={() => handleOptionClick(option)}
              disabled={!!result}
              className={`py-4 text-xl font-bold rounded-xl border-2 transition-all duration-200 shadow-sm active:scale-95 ${btnStyle}`}
            >
              {option}
            </button>
          );
        })}
      </div>

      {/* Result Message & Next Button */}
      {result && (
        <div className="animate-fade-in-up">
          <button
            onClick={handleNext}
            className="w-full py-4 bg-gray-900 text-white font-bold rounded-xl hover:bg-black transition-all shadow-lg hover:shadow-xl flex justify-center items-center gap-2"
          >
            {currentIndex + 1 === questions.length ? "View Results" : "Next Question"} 
            <span>→</span>
          </button>
        </div>
      )}
    </div>
  );
};

export default QuizCard;