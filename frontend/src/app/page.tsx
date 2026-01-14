"use client";

import Link from "next/link";

export default function Home() {
  return (
    <div className="w-full max-w-md flex flex-col gap-6 mt-10 animate-fade-in-up">
      <h2 className="text-2xl font-bold text-gray-700 text-center mb-4">Choose an Alphabet</h2>
      
      {/* Link to HIRAGANA Slug */}
      <Link href="/quiz/hiragana" className="group relative flex items-center p-6 bg-white rounded-2xl shadow-lg border-2 border-transparent hover:border-indigo-500 hover:shadow-xl transition-all duration-300">
        <div className="w-16 h-16 bg-indigo-100 rounded-full flex items-center justify-center text-3xl font-black text-indigo-600 mr-6 group-hover:scale-110 transition-transform">
          あ
        </div>
        <div className="text-left">
          <h3 className="text-xl font-bold text-gray-800">Hiragana</h3>
          <p className="text-gray-500 text-sm">The basic Japanese alphabet</p>
        </div>
      </Link>

      {/* Link to KATAKANA Slug */}
      <Link href="/quiz/katakana" className="group relative flex items-center p-6 bg-white rounded-2xl shadow-lg border-2 border-transparent hover:border-pink-500 hover:shadow-xl transition-all duration-300">
        <div className="w-16 h-16 bg-pink-100 rounded-full flex items-center justify-center text-3xl font-black text-pink-600 mr-6 group-hover:scale-110 transition-transform">
          ア
        </div>
        <div className="text-left">
          <h3 className="text-xl font-bold text-gray-800">Katakana</h3>
          <p className="text-gray-500 text-sm">For foreign words & names</p>
        </div>
      </Link>

    </div>
  );
}