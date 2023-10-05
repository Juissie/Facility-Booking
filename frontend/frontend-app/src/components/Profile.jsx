import React from 'react';
import {  useState, useEffect } from "react";
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";

function Profile() {

  // Retrieve the jwtResponse from localStorage
  //const jwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));
  const jwtResponse = {
    username: 'Hirai Momo',
    email: 'Momo@Twice.com',
    roles: ['Main Dancer'],
    profilePictureUrl: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvQOzLVWtuIaOlLcxtYyFdnQVDUHcGKTaCRQ&usqp=CAU', // Replace with your dummy roles
  };

  const [profileData, setProfileData] = useState(null);
  useEffect(() => {
    const jwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));
    
    // Define an async function to fetch profile data
    async function fetchUserProfile() {
      const storedJwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));
      const userId = storedJwtResponse.id;
      console.log(userId);
      // Check if userId is available
      if (userId) {
        // Define an async function to fetch profile data based on userId
          try {
            const config = {
              headers: {
                Authorization: `${jwtResponse.accessToken}`
              }
            };
            console.log(config);
            const response = await axios.get(`http://localhost:8080/api/user/details/${userId}`, {
              headers: {
                Authorization: `${jwtResponse.accessToken}`
              }
            }); // Replace with your API endpoint
            const User = {
              userID: userId,
              username: response.data.username,
              roles: response.data.roles,
              email: response.data.email,
              creditScore: response.data.creditScore
            }
            setProfileData(User);
            console.log(User)
          } catch (error) {
            console.error('Error fetching profile data:', error);
          }
      }
    }
    console.log(jwtResponse.accessToken);
    fetchUserProfile();
    console.log(profileData);
  }, []);
  
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
          <p><strong>Username:</strong> {profileData?.username}</p>
          <p><strong>Email:</strong> {profileData?.email}</p>
          {/* <p><strong>Roles:</strong> {profileData?.roles && profileData?.roles.join(', ')}</p> */}
          <p><strong>CreditScore:</strong> {profileData?.creditScore}</p>
        </div>
      </div>
    </div>
  );
}

export default Profile;
