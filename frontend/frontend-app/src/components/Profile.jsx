import React, { useState, useEffect } from 'react';
import MyNavbar from './NavbarComp';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';

function Profile() {
  const [profileData, setProfileData] = useState(null);

    // Retrieve the jwtResponse from localStorage
    useEffect(() => {
      const jwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));

      // console.log(jwtResponse.accessToken)

      const userId = jwtResponse.id;
      axios.get(`http://localhost:8080/api/user/details/${userId}`,
          { headers:
                {
                  Authorization : jwtResponse.accessToken,
                  withCredentials:true
                }
          }) .then(res => {
            const profile = {
              username: res.data.username,
              email: res.data.email,
              creditScore: res.data.creditScore,
              profilePictureUrl:
                'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvQOzLVWtuIaOlLcxtYyFdnQVDUHcGKTaCRQ&usqp=CAU', // Hardcoded URL for profile picture
            };
            setProfileData(profile);
          })
          .catch((error) => {
            console.error('Error fetching profile data:', error);
          });
      
      }, []);

    // Check if jwtResponse is available



  return (
    <div>
      <MyNavbar /> {/* Include your Navbar component at the top */}
      <div className="container mt-4">
        <div className="card">
          <h1>Profile</h1>
          <div className="profile-picture">
            <img
              src={profileData?.profilePictureUrl} // Use the profile picture URL from profileData
              alt="Profile Picture"
              width="150" // Adjust the width as needed
              height="150" // Adjust the height as needed
            />
          </div>
          <p>
            <strong>Username:</strong> {profileData?.username}
          </p>
          <p>
            <strong>Email:</strong> {profileData?.email}
          </p>
          <p>
            <strong>CreditScore:</strong> {profileData?.creditScore}
          </p>
        </div>
      </div>
    </div>
  );
}

export default Profile;
