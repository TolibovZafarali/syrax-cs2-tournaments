
import { useNavigate } from 'react-router-dom';

function Login() {
  const navigate = useNavigate();

  const handleAdminLogin = () => {
    // Dummy logic for now, you’ll replace with real auth
    const isAdmin = window.prompt("Enter admin password:");
    if (isAdmin === "syraxadmin123") {
      navigate("/admin");
    } else {
      alert("Incorrect password");
    }
  };

  const handleSteamLogin = () => {
    // Redirect to backend route for Steam OAuth
    window.location.href = "http://localhost:8080/auth/steam"; // adjust backend port as needed
  };

  return (
    <div style={{ textAlign: 'center' }}>
      <h2>Login</h2>
      <button onClick={handleAdminLogin}>Admin Login</button>
      <br /><br />
      <button onClick={handleSteamLogin}>Login with Steam</button>
    </div>
  );
}

export default Login;
