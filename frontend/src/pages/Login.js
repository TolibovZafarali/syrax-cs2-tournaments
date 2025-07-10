import { useNavigate } from 'react-router-dom';
import api from '../api/axios';

function Login() {
  const navigate = useNavigate();

  const handleAdminLogin = async () => {
    const password = window.prompt('Enter admin password:');
    if (!password) return;
    try {
      await api.post('/admin/login', { password });
      navigate('/admin');
    } catch (e) {
      alert('Incorrect password');
    }
  };

  const handleSteamLogin = () => {
    window.location.href = 'http://localhost:8080/auth/steam';
  };

  return (
    <div className="p-8 text-center space-y-4">
      <h2 className="text-xl font-semibold">Login</h2>
      <button className="px-4 py-2 bg-blue-600 text-white" onClick={handleAdminLogin}>Admin Login</button>
      <br />
      <button className="px-4 py-2 bg-green-600 text-white" onClick={handleSteamLogin}>Login with Steam</button>
    </div>
  );
}

export default Login;
