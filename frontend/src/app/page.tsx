import QuizCard from "@/components/QuizCard";

export default function Home() {
  return (
    <main className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-indigo-50 to-blue-100 p-4">
      <div className="text-center mb-8">
        <h1 className="text-4xl font-extrabold text-gray-900 tracking-tight mb-2">
          Nihon<span className="text-indigo-600">Go</span> Master
        </h1>
        <p className="text-gray-600">Master Japanese characters one click at a time.</p>
      </div>
      
      <QuizCard />
    </main>
  );
}