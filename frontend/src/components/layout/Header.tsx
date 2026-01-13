import React from 'react';

const Header = () => {
  return (
    <header className="w-full py-8 text-center bg-transparent">
      <h1 className="text-4xl font-extrabold text-gray-900 tracking-tight mb-2">
        Nihon<span className="text-indigo-600">Go</span> Master
      </h1>
      <p className="text-gray-600 text-lg">
        Master Japanese characters one click at a time.
      </p>
    </header>
  );
};

export default Header;