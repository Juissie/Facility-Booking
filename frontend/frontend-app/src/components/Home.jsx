import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

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
    <html lang="en">
      <head>
        <meta charset="UTF-8" />
        <title>Home</title>
        <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
        />
      </head>
      <body>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
          <div className="container-fluid">
            <a className="navbar-brand">Facility Booking Home Page</a>
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
              <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                  <a
                    className="nav-link active"
                    aria-current="page"
                    onClick={logout}
                    href="/login"
                  >
                    Logout
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <br />
        <br />
        <div className="container">
          <div className="row">
            <h1>Welcome to the facility booking page</h1>
          </div>
        </div>
      </body>
    </html>
  );
}

export default HomePage;
