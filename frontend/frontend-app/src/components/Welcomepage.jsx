import React from 'react';

function WelcomePage() {
  return (
    <html lang="en">
      <head>
        <meta charset="UTF-8" />
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0"
        />
        <title>Welcome page</title>
        <style>
          {`
            body {
              display: flex;
              justify-content: center;
              align-items: center;
              height: 100vh;
              margin: 0;
            }
            .content {
              text-align: center;
            }
          `}
        </style>
      </head>
      <body>
        <div className="content">
          <h1>Facility Booking System</h1>
          <p>
            <a href="/register">Customer Registration</a>
          </p>
          <p>
            <a href="/login">Customer Login</a>
          </p>
        </div>
      </body>
    </html>
  );
}

export default WelcomePage;
