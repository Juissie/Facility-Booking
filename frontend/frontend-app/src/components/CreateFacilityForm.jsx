import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function CreateFacilityForm() {
  const [facilityType, setFacilityType] = useState("");
  const [description, setDescription] = useState("");
  const navigate = useNavigate();

  async function createFacility(event) {
    event.preventDefault();

    try {
      // Get the JWT token from localStorage
      const jwtResponse = JSON.parse(localStorage.getItem('jwtResponse'));
      const authToken = jwtResponse.accessToken;

      // Include the JWT token in the request headers
      const headers = {
        Authorization: authToken,
        'Content-Type': 'application/json',
      };

      // Send a POST request to create a facility with headers
      await axios.post("http://localhost:8080/api/facilities", {
        facilityType: facilityType,
        description: description,
      }, { headers });

      alert("Facility created successfully");
      // Redirect to a different page, e.g., facility listing page
      navigate('/facilities');
    } catch (error) {
      console.error("Error:", error);
      // Handle error messages or show a generic error message
      alert("Failed to create facility");
    }
  }

  return (
    <div>
      <h1>Create Facility</h1>
      <form onSubmit={createFacility}>
        <div className="form-group">
          <label>Facility Type</label>
          <input
            type="text"
            className="form-control"
            value={facilityType}
            onChange={(e) => setFacilityType(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Description</label>
          <textarea
            className="form-control"
            rows="4"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          ></textarea>
        </div>
        <button type="submit" className="btn btn-primary">
          Create Facility
        </button>
      </form>
    </div>
  );
}

export default CreateFacilityForm;


