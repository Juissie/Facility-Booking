import React from 'react';
import { Link } from 'react-router-dom';
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';

function Profile() {

  // Retrieve the jwtResponse from localStorage
  //const jwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));
  const jwtResponse = {
    username: 'Hirai Momo',
    email: 'Momo@Twice.com',
    roles: ['Main Dancer'],
    profilePictureUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvQOzLVWtuIaOlLcxtYyFdnQVDUHcGKTaCRQ&usqp=CAU', // Replace with your dummy roles
  };

  return (
    <div>
      <MyNavbar /> {/* Include your Navbar component at the top */}
      <div className="container mt-4">
        <div className="card">
          <h1>Profile</h1>
          <div className="profile-picture">
            <img
              src={jwtResponse.profilePictureUrl} // Provide the URL or source for the profile picture
              alt="Profile Picture"
              width="150" // Adjust the width as needed
              height="150" // Adjust the height as needed
            />
          </div>
          <p><strong>Username:</strong> {jwtResponse.username}</p>
          <p><strong>Email:</strong> {jwtResponse.email}</p>
          <p><strong>Roles:</strong> {jwtResponse.roles && jwtResponse.roles.join(', ')}</p>
        </div>
      </div>
    </div>
  );
}

export default Profile;
