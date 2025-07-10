import { useState } from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
  const [open, setOpen] = useState(false);

  const toggle = () => setOpen(!open);

  return (
    <nav className="bg-gray-800 text-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          <div className="flex-shrink-0">
            <Link to="/" className="text-xl font-semibold">Syrax</Link>
          </div>
          <div className="md:hidden">
            <button
              onClick={toggle}
              className="text-gray-300 hover:text-white focus:outline-none"
            >
              <svg
                className="h-6 w-6"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M4 6h16M4 12h16M4 18h16"
                />
              </svg>
            </button>
          </div>
          <div className="hidden md:block">
            <div className="ml-10 flex items-baseline space-x-4">
              <Link to="/" className="hover:underline">
                Home
              </Link>
              <Link to="/tournaments" className="hover:underline">
                Tournaments
              </Link>
              <Link to="/create" className="hover:underline">
                Create Tournament
              </Link>
              <Link to="/admin" className="hover:underline">
                Admin Panel
              </Link>
            </div>
          </div>
        </div>
      </div>
      {open && (
        <div className="px-2 pt-2 pb-3 space-y-1 md:hidden">
          <Link to="/" className="block px-3 py-2 hover:bg-gray-700 rounded-md">
            Home
          </Link>
          <Link
            to="/tournaments"
            className="block px-3 py-2 hover:bg-gray-700 rounded-md"
          >
            Tournaments
          </Link>
          <Link
            to="/create"
            className="block px-3 py-2 hover:bg-gray-700 rounded-md"
          >
            Create Tournament
          </Link>
          <Link
            to="/admin"
            className="block px-3 py-2 hover:bg-gray-700 rounded-md"
          >
            Admin Panel
          </Link>
        </div>
      )}
    </nav>
  );
}

export default Navbar;
