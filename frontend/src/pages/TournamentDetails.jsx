import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import api from '../api/axios';

function TournamentDetails() {
  const { id } = useParams();
  const [tournament, setTournament] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchTournament() {
      try {
        const response = await api.get(`/tournaments/${id}`);
        setTournament(response.data);
      } catch (err) {
        setError('Failed to load tournament');
      } finally {
        setLoading(false);
      }
    }

    fetchTournament();
  }, [id]);

  if (loading) {
    return <div className="p-8 text-center">Loading...</div>;
  }

  if (error) {
    return <div className="p-8 text-center text-red-500">{error}</div>;
  }

  if (!tournament) return null;

  return (
    <div className="p-8">
      <h2 className="text-xl font-semibold mb-4">{tournament.name}</h2>
      <div className="space-y-1 mb-6">
        <div>Status: {tournament.status}</div>
        <div>Game: {tournament.game}</div>
        <div>Team Size: {tournament.teamSize}</div>
        <div>Prize Pool: {tournament.prizePool}</div>
        <div>Start Time: {tournament.startTime}</div>
      </div>

      {tournament.teams && tournament.teams.length > 0 && (
        <div className="mb-6">
          <h3 className="font-semibold mb-2">Teams</h3>
          <ul className="list-disc list-inside space-y-1">
            {tournament.teams.map(team => (
              <li key={team.id}>
                <div className="font-medium">{team.name}</div>
                {team.players && team.players.length > 0 && (
                  <ul className="list-disc list-inside ml-4">
                    {team.players.map(player => (
                      <li key={player.id}>{player.username || player.steamId}</li>
                    ))}
                  </ul>
                )}
              </li>
            ))}
          </ul>
        </div>
      )}

      {tournament.matches && tournament.matches.length > 0 && (
        <div>
          <h3 className="font-semibold mb-2">Matches</h3>
          <ul className="list-disc list-inside space-y-1">
            {tournament.matches.map(match => (
              <li key={match.id}>
                <div>Round {match.round}: Team {match.teamAId} vs Team {match.teamBId}</div>
                <div>Lobby: {match.lobbyLink}</div>
                {match.winnerId && (
                  <div>Winner: {match.winnerId}</div>
                )}
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}

export default TournamentDetails;
