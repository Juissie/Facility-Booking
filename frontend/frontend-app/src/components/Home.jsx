import React from 'react';

function HomePage() {
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
