import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // or wherever your Spring Boot backend runs
  headers: {
    'Content-Type': 'application/json'
  }
});

export default api;
