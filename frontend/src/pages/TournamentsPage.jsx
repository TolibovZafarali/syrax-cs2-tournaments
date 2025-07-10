import { useState, useEffect } from 'react';
import api from '../api/axios';

function TournamentsPage() {
  const [tournaments, setTournaments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm('Are you sure you want to delete this tournament?');
    if (!confirmDelete) return;
    try {
      await api.delete(`/tournaments/${id}`);
      setTournaments((prev) => prev.filter((t) => t.id !== id));
    } catch (err) {
      console.error(err);
      alert('Failed to delete tournament');
    }
  };

  useEffect(() => {
    async function fetchTournaments() {
      try {
        const response = await api.get('/tournaments');
        setTournaments(response.data);
      } catch (err) {
        setError('Failed to load tournaments');
      } finally {
        setLoading(false);
      }
    }

    fetchTournaments();
  }, []);

  if (loading) {
    return <div className="p-8 text-center">Loading...</div>;
  }

  if (error) {
    return <div className="p-8 text-center text-red-500">{error}</div>;
  }

  return (
    <div className="p-8">
      <h2 className="text-xl font-semibold mb-4">Tournaments</h2>
      <ul className="space-y-2">
        {tournaments.map((tournament) => (
          <li key={tournament.id} className="border p-4 rounded">
            <div className="font-bold flex items-center justify-between">
              <span>{tournament.name}</span>
              <button
                onClick={() => handleDelete(tournament.id)}
                className="text-red-500 hover:text-red-700 text-sm"
              >
                Delete
              </button>
            </div>
            <div>Status: {tournament.status}</div>
            <div>Game: {tournament.game}</div>
            <div>Start Time: {tournament.startTime}</div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default TournamentsPage;
