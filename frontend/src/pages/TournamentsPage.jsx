import { useState, useEffect } from 'react';
import api from '../api/axios';

function TournamentsPage() {
  const [tournaments, setTournaments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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
            <div className="font-bold">{tournament.name}</div>
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
