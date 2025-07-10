import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

function TournamentForm() {
  const [formData, setFormData] = useState({
    name: '',
    game: '',
    teamSize: '',
    prizePool: '',
    startTime: '',
    status: 'UPCOMING',
  });

  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setSuccess(null);
    try {
      await api.post('/tournaments', {
        ...formData,
        teamSize: Number(formData.teamSize),
        prizePool: Number(formData.prizePool),
      });
      setSuccess('Tournament created successfully');
      setTimeout(() => {
        navigate('/tournaments');
      }, 1000);
    } catch (err) {
      setError('Failed to create tournament');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="p-4 space-y-4 max-w-md mx-auto">
      {error && <div className="text-red-600">{error}</div>}
      {success && <div className="text-green-600">{success}</div>}
      <div>
        <label className="block mb-1">Name</label>
        <input
          className="border p-2 w-full"
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label className="block mb-1">Game</label>
        <input
          className="border p-2 w-full"
          type="text"
          name="game"
          value={formData.game}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label className="block mb-1">Team Size</label>
        <input
          className="border p-2 w-full"
          type="number"
          name="teamSize"
          value={formData.teamSize}
          onChange={handleChange}
          min="1"
          required
        />
      </div>
      <div>
        <label className="block mb-1">Prize Pool</label>
        <input
          className="border p-2 w-full"
          type="number"
          name="prizePool"
          value={formData.prizePool}
          onChange={handleChange}
          min="0"
          required
        />
      </div>
      <div>
        <label className="block mb-1">Start Time</label>
        <input
          className="border p-2 w-full"
          type="datetime-local"
          name="startTime"
          value={formData.startTime}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label className="block mb-1">Status</label>
        <select
          className="border p-2 w-full"
          name="status"
          value={formData.status}
          onChange={handleChange}
        >
          <option value="UPCOMING">UPCOMING</option>
          <option value="ONGOING">ONGOING</option>
          <option value="COMPLETED">COMPLETED</option>
          <option value="CANCELLED">CANCELLED</option>
        </select>
      </div>
      <button type="submit" className="px-4 py-2 bg-blue-600 text-white">
        Create Tournament
      </button>
    </form>
  );
}

export default TournamentForm;
