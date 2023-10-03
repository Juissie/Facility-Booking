import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';

function HomePage() {
  const navigate = useNavigate();

  // Logout function to remove the JWT token from localStorage
  function logout() {
    localStorage.removeItem('jwtResponse');
    // Redirect to the login page or any other desired page
    navigate('/login');
  }

  useEffect(() => {
    // Check if the JWT token is present in localStorage
    const jwtToken = localStorage.getItem('jwtResponse');

    // If not authenticated, redirect to the login page
    if (jwtToken == null) {
      navigate('/login');
    }
  }, [navigate]);
  return (
<div>
  <MyNavbar /> 
  <div className="container">
    <div className="row">
      <h1>Welcome to the facility booking page</h1>
    </div>
  </div>
</div>
  );
}

export default HomePage;
